package dev.ericksuarez.roomies.ledger.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.ericksuarez.roomies.ledger.service.model.Purchase;
import dev.ericksuarez.roomies.ledger.service.repository.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;


    public List<Purchase> getAllPurchase() {
        return purchaseRepository.findAll();
    }

    public Optional<Purchase> findPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }
/*
    public List<Purchase> getAllPurchaseByUserId(Long userId) {
        return purchaseRepository.getAllByUserId(userId);
    }

    public List<Purchase> getAllPurchaseByHouseId(Long houseId) {
        return purchaseRepository.getAllByHouseId(houseId);
    }

 */

    public Purchase createOrUpdatePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public ResponseEntity<?> deletePurchase(Purchase purchase){
        purchaseRepository.delete(purchase);
        return ResponseEntity.ok().build();
    }
}
