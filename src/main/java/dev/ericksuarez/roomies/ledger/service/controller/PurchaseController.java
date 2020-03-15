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

import dev.ericksuarez.roomies.ledger.service.model.Purchase;
import dev.ericksuarez.roomies.ledger.service.service.PurchaseService;

import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.API;
import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.GET_PURCHASES_FROM_HOUSE;
import static dev.ericksuarez.roomies.ledger.service.config.RoomiesUri.PURCHASE;

@RestController
@RequestMapping(API)
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @GetMapping(PURCHASE + "/{id}")
    Purchase findPurchaseById(@PathVariable(value = "id") @Min(1) Long id) {
        return purchaseService.findPurchaseById(id);
    }

    @PostMapping(PURCHASE)
    Purchase createPurchase(@Valid Purchase purchase) {
        return purchaseService.createOrUpdatePurchase(purchase);
    }

    @PutMapping(PURCHASE + "/{id}")
    Purchase updatePurchase(@PathVariable(value = "id") @Min(1) Long id, @Valid @RequestBody Purchase purchase) {
        purchase.setId(id);
        return purchaseService.createOrUpdatePurchase(purchase);
    }

    @DeleteMapping(PURCHASE + "/{id}")
    ResponseEntity<?> deletePurchase(@PathVariable(value = "id") @Min(1) Long id) {
        return purchaseService.deletePurchase(id);
    }

    @GetMapping(GET_PURCHASES_FROM_HOUSE + "/{houseId}")
    List<Purchase> getPurchasesByHousesId(@PathVariable(value = "houseId") @Min(1) Long houseId) {
        return purchaseService.getAllPurchaseByHouseId(houseId);
    }
}
