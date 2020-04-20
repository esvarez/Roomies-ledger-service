package dev.ericksuarez.roomies.ledger.service.service;

import dev.ericksuarez.roomies.ledger.service.model.entity.Account;
import dev.ericksuarez.roomies.ledger.service.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

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

    public Object getAccountsByUserId(UUID userId) {
        log.info("event=getAccountsByUserIdInvoked userId={}", userId);
        Set<Account> accounts = accountRepository.findAccountByUsersId(userId);
        log.info("event=getAccountsByUserIdInvoked accounts={}", accounts);
        return null;
    }

    public Set<Account> findAccountByUsersId(UUID userId) {
        log.info("event=findAccountByUsersIdInvoked userId={}", userId);
        return accountRepository.findAccountByUsersId(userId);
    }

    public Account saveAccount(Account account) {
        log.info("event=saveAccountInvoked account={}", account);
        return accountRepository.save(account);
    }

}
