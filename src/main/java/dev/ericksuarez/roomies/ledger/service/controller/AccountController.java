package dev.ericksuarez.roomies.ledger.service.controller;

import dev.ericksuarez.roomies.ledger.service.model.entity.Account;
import dev.ericksuarez.roomies.ledger.service.model.response.AccountResponse;
import dev.ericksuarez.roomies.ledger.service.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.ACCOUNT;
import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.API;
import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.GET_ACCOUNTS_FROM_USER;

@Slf4j
@RestController
@RequestMapping(API)
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(ACCOUNT + "/{id}")
    public Account findAccountById(@PathVariable("id") @Min(1) Long id) {
        log.info("event=findAccountByIdInvoked id={}", id);
        return accountService.getAccountById(id);
    }

    @GetMapping(GET_ACCOUNTS_FROM_USER + "/{userId}")
    public List<AccountResponse> findAccountByUserId(@PathVariable String userId) {
        log.info("event=findAccountByUserIdInvoked userId={}", userId);
        return accountService.getAccountsByUserId(UUID.fromString(userId));
    }

    @PostMapping(ACCOUNT)
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@Valid @RequestBody Account account) {
        log.info("event=createAccountInvoked account={}", account);
        return accountService.saveAccount(account);
    }

    @PutMapping(ACCOUNT + "/{id}")
    public Account updateAccount(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody Account account) {
        log.info("event=updateAccountInvoked id={} account={}", id, account);
        account.setId(id);
        return accountService.saveAccount(account);
    }
}
