package dev.ericksuarez.roomies.ledger.service.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ericksuarez.roomies.ledger.service.model.entity.Ledger;
import dev.ericksuarez.roomies.ledger.service.service.LedgerService;

import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.API;
import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.GET_LEDGER_FROM_USER;
import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.LEDGER;

@Slf4j
@RestController
@RequestMapping(API)
public class LedgerController {

    @Autowired
    LedgerService ledgerService;

    @GetMapping(LEDGER + "/{id}")
    public Ledger findLedgerById(@PathVariable(value = "id") @Min(1) Long id) {
        log.info("event=findLedgerByIdInvoked id={}", id);
        return ledgerService.findLedgerById(id);
    }

    @PutMapping(LEDGER + "/{id}")
    public Ledger updateLedger(@PathVariable(value = "id") @Min(1) Long id, @Valid @RequestBody Ledger ledger) {
        log.info("event=updateLedgerInvoked id={} ledger={}", id, ledger);
        ledger.setId(id);
        return ledgerService.createOrUpdateLedger(ledger);
    }

    @DeleteMapping(LEDGER)
    public ResponseEntity<?> deleteLedger(@PathVariable(value = "id") @Min(1) Long id){
        log.info("event=deleteLedgerInvoked id={}", id);
        return ledgerService.deleteLedger(id);
    }

    @GetMapping(GET_LEDGER_FROM_USER + "/{userId}")
    public List<Ledger> getLedgersByUserId(@PathVariable(value = "userId") @Min(1) Long userId) {
        log.info("event=getLedgersByUserIdInvoked userId={}", userId);
        return ledgerService.getAllLedgersByUserId(userId);
    }

}
