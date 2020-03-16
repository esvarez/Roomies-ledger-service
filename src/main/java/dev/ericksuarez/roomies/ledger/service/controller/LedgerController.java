package dev.ericksuarez.roomies.ledger.service.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ericksuarez.roomies.ledger.service.model.Ledger;
import dev.ericksuarez.roomies.ledger.service.service.LedgerService;

import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.API;
import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.GET_LEDGER_FROM_USER;
import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.LEDGER;

@RestController
@RequestMapping(API)
public class LedgerController {

    @Autowired
    LedgerService ledgerService;

    @GetMapping(LEDGER + "/{id}")
    Ledger findLedgerById(@PathVariable(value = "id") @Min(1) Long id) {
        return ledgerService.findLedgerById(id);
    }

    @PostMapping(LEDGER)
    Ledger createLedger(@Valid @RequestBody Ledger ledger) {
        return ledgerService.createOrUpdateLedger(ledger);
    }

    @PutMapping(LEDGER + "/{id}")
    Ledger updateLedger(@PathVariable(value = "id") @Min(1) Long id, @Valid @RequestBody Ledger ledger) {
        ledger.setId(id);
        return ledgerService.createOrUpdateLedger(ledger);
    }

    @DeleteMapping
    ResponseEntity<?> deleteLedger(@PathVariable(value = "id") @Min(1) Long id){
        return ledgerService.deleteLedger(id);
    }

    @GetMapping(GET_LEDGER_FROM_USER + "/{userId}")
    List<Ledger> getLedgersByUserId(@PathVariable(value = "userId") @Min(1) Long userId) {
        return ledgerService.getAllLedgersByUserId(userId);
    }

}
