package dev.ericksuarez.roomies.ledger.service.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.ericksuarez.roomies.ledger.service.model.entity.Purchase;
import dev.ericksuarez.roomies.ledger.service.service.PurchaseService;

import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.API;
import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.GET_PURCHASES_FROM_UNIT;
import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.PURCHASE;

@Slf4j
@RestController
@RequestMapping(API)
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @GetMapping(PURCHASE + "/{id}")
    public Purchase findPurchaseById(@PathVariable(value = "id") @Min(1) Long id) {
        log.info("event=findPurchaseByIdInvoked id={}", id);
        return purchaseService.findPurchaseById(id);
    }
/*
    @PostMapping(PURCHASE)
    public Purchase createPurchase(@Valid @RequestBody PurchaseDto purchase) {
        log.info("event=createPurchaseInvoked purchase={}", purchase);
        return purchaseService.createPurchase(purchase);
    }
 */

    @PutMapping(PURCHASE + "/{id}")
    public Purchase updatePurchase(@PathVariable(value = "id") @Min(1) Long id, @Valid @RequestBody Purchase purchase) {
        log.info("event=updatePurchaseInvoked id={}, purchase={}", id, purchase);
        purchase.setId(id);
        return purchaseService.createOrUpdatePurchase(purchase);
    }

    @DeleteMapping(PURCHASE + "/{id}")
    public ResponseEntity<?> deletePurchase(@PathVariable(value = "id") @Min(1) Long id) {
        log.info("event=deletePurchaseInvoked id={}", id);
        return purchaseService.deletePurchase(id);
    }

    @GetMapping(GET_PURCHASES_FROM_UNIT + "/{houseId}")
    public List<Purchase> getPurchasesByHousesId(@PathVariable(value = "houseId") @Min(1) Long houseId,
                                          @RequestParam(name = "from")Optional<LocalDate> startDate,
                                          @RequestParam(name = "to")Optional<LocalDate> endDate) {
        log.info("event=getPurchasesByHousesIdInvoked houseId={} startDate={} endDate={}", houseId, startDate, endDate);
        return purchaseService.getAllPurchaseByHouseId(houseId, startDate, endDate);
    }
}
