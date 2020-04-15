package dev.ericksuarez.roomies.ledger.service.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import dev.ericksuarez.roomies.ledger.service.model.entity.Ledger;
import dev.ericksuarez.roomies.ledger.service.model.PurchaseDto;
import dev.ericksuarez.roomies.ledger.service.model.entity.relations.User;
import dev.ericksuarez.roomies.ledger.service.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.ericksuarez.roomies.ledger.service.model.entity.Purchase;
import dev.ericksuarez.roomies.ledger.service.repository.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private LedgerRepository ledgerRepository;

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

    private boolean isOwnPurchase(Long userId, Long ownPurchase){
        return userId == ownPurchase;
    }

    public List<Purchase> getAllPurchase() {
        return purchaseRepository.findAll();
    }

    public Purchase findPurchaseById(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Not Found"));
    }

    public List<Purchase> getAllPurchaseByUserId(Long userId) {
        return purchaseRepository.getAllByUserId(userId);
    }

    public List<Purchase> getAllPurchaseByHouseId(Long houseId) {
        return purchaseRepository.getAllByHouseId(houseId);
    }

    public Purchase createOrUpdatePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public ResponseEntity<?> deletePurchase(Long id){
        purchaseRepository.findById(id)
                .map( purchase -> {
                    purchaseRepository.delete(purchase);
                    return ResponseEntity.ok().build();
                });
        return ResponseEntity.ok().build();
    }
}
