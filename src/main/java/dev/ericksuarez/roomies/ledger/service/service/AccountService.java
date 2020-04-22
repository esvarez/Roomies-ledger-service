package dev.ericksuarez.roomies.ledger.service.service;

import dev.ericksuarez.roomies.ledger.service.model.entity.Account;
import dev.ericksuarez.roomies.ledger.service.model.entity.relations.User;
import dev.ericksuarez.roomies.ledger.service.model.response.AccountResponse;
import dev.ericksuarez.roomies.ledger.service.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(Long accountId) {
        log.info("event=getAccountByIdInvoked accountId={}", accountId);
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not exist"));
    }

    public List<AccountResponse> getAccountsByUserId(UUID userId) {
        log.info("event=getAccountsByUserIdInvoked userId={}", userId);
        Set<Account> accounts = accountRepository.findAccountsByUsersId(userId);
        log.info("event=accountGetting accounts={}", accounts);
        List<AccountResponse> accountResponses = accounts.stream()
                .map(account -> {
                    BigDecimal amount = account.getUserCred().equals(userId)
                            ? account.getAmountDebt()
                            : account.getAmountDebt().negate();
                    String userName = getUserName(account.getUsers(), userId);
                    return AccountResponse.builder()
                            .accountId(account.getId())
                            .userName(userName)
                            .amount(amount)
                            .build();
                }).collect(Collectors.toList());

        log.info("event=responseBuilt accountResponses={}", accountResponses);
        return accountResponses;
    }

    public Set<Account> findAccountByUsersId(UUID userId) {
        log.info("event=findAccountByUsersIdInvoked userId={}", userId);
        return accountRepository.findAccountsByUsersId(userId);
    }

    public Account saveAccount(Account account) {
        log.info("event=saveAccountInvoked account={}", account);
        return accountRepository.save(account);
    }

    private String getUserName(Set<User> users, UUID userOwner) {
        return users.stream()
                .filter(user -> !user.getId().equals(userOwner))
                .map(user -> user.getName())
                .findAny()
                .get();
    }

}
