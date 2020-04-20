package dev.ericksuarez.roomies.ledger.service.controller;

import dev.ericksuarez.roomies.ledger.service.facade.AccountFacade;
import dev.ericksuarez.roomies.ledger.service.model.dto.PurchaseDto;
import dev.ericksuarez.roomies.ledger.service.model.entity.Account;
import dev.ericksuarez.roomies.ledger.service.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import java.net.URI;
import java.util.UUID;

import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.ACCOUNT;
import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.API;
import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.USERS;

@Slf4j
@RestController
@RequestMapping(API)
public class AccountController {

    private AccountFacade accountFacade;

    private AccountService accountService;

    @Autowired
    public AccountController(AccountFacade accountFacade, AccountService accountService) {
        this.accountFacade = accountFacade;
        this.accountService = accountService;
    }

    @GetMapping(ACCOUNT + "/{id}")
    public Account findAccountById(@PathVariable(value = "id") @Min(1) Long id) {
        log.info("event=findAccountByIdInvoked id={}", id);
        return accountService.getAccountById(id);
    }

    @GetMapping(USERS + "/{uerId}" + ACCOUNT)
    public Object findAccountByUserId(@PathVariable(value = "userId") UUID userId) {
        log.info("event=findAccountByUserIdInvoked userId={}", userId);
        return null;
    }

    @PostMapping(ACCOUNT)
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@Valid @RequestBody PurchaseDto purchaseDto) {
        log.info("event=createAccountInvoked purchaseDto={}", purchaseDto);
        accountFacade.transactPurchase(purchaseDto);
        //return ResponseEntity.created(URI.create("http")).build();
    }

    @PutMapping(ACCOUNT + "/{id}")
    public Account updateAccount(@PathVariable(value = "id") @Min(1) Long id, @Valid @RequestBody Account account) {
        log.info("event=updateAccountInvoked id={} account={}", id, account);
        account.setId(id);
        return accountService.saveAccount(account);
    }
}
