package dev.ericksuarez.roomies.ledger.service.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.ericksuarez.roomies.ledger.service.model.entity.Ledger;
import dev.ericksuarez.roomies.ledger.service.model.entity.relations.User;
import dev.ericksuarez.roomies.ledger.service.repository.LedgerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.ericksuarez.roomies.ledger.service.model.entity.Purchase;
import dev.ericksuarez.roomies.ledger.service.repository.PurchaseRepository;

import javax.swing.text.html.Option;

@Slf4j
@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;
/*
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
*/
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

    public List<Purchase> getAllPurchaseByUserId(UUID userId) {
        return purchaseRepository.getAllByUserId(userId);
    }

    public List<Purchase> getAllPurchaseByHouseId(Long houseId, Optional<LocalDate> startDate, Optional<LocalDate> endDate) {
        log.info("event=getAllPurchaseByHouseIdInvoked houseId={} startDate={} endDate={}", houseId, startDate, endDate);
        if (startDate.isPresent() && endDate.isPresent()){
            log.info("event=datesPresent");
            return purchaseRepository.getAllByUnitIdAndDate(houseId, startDate.get(), endDate.get());
        }
        log.info("event=getPurchaseById");
        return purchaseRepository.getAllByUnitId(houseId);
    }

    public Purchase createOrUpdatePurchase(Purchase purchase) {
        log.info("event=createOrUpdatePurchaseInvoked purchase={}", purchase);
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
