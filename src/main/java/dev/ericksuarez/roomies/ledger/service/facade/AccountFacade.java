package dev.ericksuarez.roomies.ledger.service.facade;

import dev.ericksuarez.roomies.ledger.service.model.dto.PurchaseDto;
import dev.ericksuarez.roomies.ledger.service.model.entity.Account;
import dev.ericksuarez.roomies.ledger.service.model.entity.Purchase;
import dev.ericksuarez.roomies.ledger.service.model.entity.relations.User;
import dev.ericksuarez.roomies.ledger.service.repository.UserRepository;
import dev.ericksuarez.roomies.ledger.service.service.AccountService;
import dev.ericksuarez.roomies.ledger.service.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class AccountFacade {

    private AccountService accountService;

    private PurchaseService purchaseService;

    private UserRepository userRepository;

    @Autowired
    public AccountFacade(AccountService accountService, PurchaseService purchaseService, UserRepository userRepository) {
        this.accountService = accountService;
        this.purchaseService = purchaseService;
        this.userRepository = userRepository;
    }

    public void transactPurchase(PurchaseDto purchaseDto) {
        log.info("event=transactPurchaseInvoked purchaseDto={}", purchaseDto);
        final Purchase purchase = Purchase.builder()
                .user(purchaseDto.getUserBuyer())
                .price(purchaseDto.getPrice())
                .description(purchaseDto.getDescription())
                .photo(purchaseDto.getPhoto())
                .date(purchaseDto.getDate())
                .build();
        log.info("event=purchaseToStorage purchase={}", purchase);
        purchaseService.createOrUpdatePurchase(purchase);
        BigDecimal total = BigDecimal.valueOf(purchaseDto.getUsersToSplit().size() + 1);
        BigDecimal amount = purchase.getPrice().divide(total, 2, RoundingMode.HALF_UP);
        log.info("event=amountSplited amount={}", amount);
        User userBuyer = purchaseDto.getUserBuyer();
        userBuyer.setAccounts(accountService.findAccountByUsersId(userBuyer.getId()));
        Set<User> users = purchaseDto.getUsersToSplit();

        log.info("event=usersToSplitCost userBuyer={} users={}", userBuyer, users);
        users.stream()
                .map(user -> getCommonAccount(user, userBuyer.getAccounts()))
                .peek(user -> createAccountOrTransaction(userBuyer, user, amount))
                .forEach(System.out::println);

        log.info("event=transactionDone userBuyer={} users={} amount={}", userBuyer, users, amount);
    }

    private User getCommonAccount(User user, Set<Account> buyerAccounts) {
        log.info("event=getCommonAccountInvoked user={}, buyerAccounts={}", user, buyerAccounts);
        Set<Account> accounts = accountService.findAccountByUsersId(user.getId());
        accounts.retainAll(buyerAccounts);
        log.info("event=gettingCommonAccount accounts={}", accounts);
        user.setAccounts(accounts);
        log.info("event=userReturned user={}", user);
        return user;
    }

    private void createAccountOrTransaction(User userBuyer, User user, BigDecimal amount) {
        log.info("event=createAccountOrTransactionInvoked userBuyer={} user={} amount={}", userBuyer, user, amount);
        if (user.getAccounts().isEmpty()) {
            log.info("event=creatingCommonAccount commonAccount={}", user.getAccounts());
            Account account = Account.builder()
                    .users(Set.of(userBuyer, user))
                    .userCred(userBuyer.getId())
                    .userDebt(user.getId())
                    .amountDebt(amount)
                    .users(new HashSet<>())
                    .build();
            // TODO add account to user
            log.info("event=accounts account={}", account);

            /*userBuyer = userRepository.findById(userBuyer.getId())
                    .orElseThrow(() -> new RuntimeException("Code trash"));*/
            userBuyer.getAccounts().add(account);
            /*user = userRepository.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("Code trash"));*/
            user.getAccounts().add(account);
            account.getUsers().add(userBuyer);
            account.getUsers().add(user);
            log.info("event=presave");
            log.info("event=accountBuilt account={}", account);
            accountService.saveAccount(account);
            log.info("event=accountSaved user={} userBuyer={}", user, userBuyer);
            //userRepository.save(user);
            //userRepository.save(userBuyer);
        } else {
            Account account = accountService.getAccountById(user.getAccounts().stream().findFirst().get().getId());
            log.info("event=transactingNewPurchase account={}", account);
            if (account.getUserCred() == userBuyer.getId()) {
                log.info("event=addToDebt account={}", account);
                account.setAmountDebt(account.getAmountDebt().add(amount));
            } else {
                log.info("event=subtractToDebt account={}", account);
                account.setAmountDebt(account.getAmountDebt().subtract(amount));
            }
            log.info("event=AccountToSave account={}", account);
            accountService.saveAccount(account);
        }
    }

    /*
    public Purchase createPurchase(PurchaseDto purchaseDto) {
        final Purchase purchase = Purchase.builder()
                .user(purchaseDto.getUser())
                .price(purchaseDto.getPrice())
                .description(purchaseDto.getDescription())
                .photo(purchaseDto.getPhoto())
                .date(purchaseDto.getDate())
                .build();

        createOrUpdatePurchase(purchase);

        BigDecimal amount = purchase.getPrice().divide(BigDecimal.valueOf(purchaseDto.getUsersId().size() + 1));
        List<Ledger> ledgers = purchaseDto.getUsersId().stream()
                .map(id -> {
                    return Ledger.builder()
                            .purchaseId(purchase)
                            .user(User.builder()
                                .id(id)
                                .build())
                            .amountDebt(amount)
                            .amountPaid(BigDecimal.ZERO)
                            .build();
                })
                .collect(Collectors.toList());

        ledgers.add(Ledger.builder()
                .purchaseId(purchase)
                .user(User.builder()
                    .id(purchaseDto.getUser().getId())
                    .build())
                .amountDebt(amount)
                .amountPaid(amount)
                .paid(true)
                .build());

        ledgerRepository.saveAll(ledgers);
        return purchase;
    }
*/
}
