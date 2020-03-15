package dev.ericksuarez.roomies.ledger.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.ericksuarez.roomies.ledger.service.model.Ledger;
import dev.ericksuarez.roomies.ledger.service.model.Purchase;
import dev.ericksuarez.roomies.ledger.service.repository.LedgerRepository;

@Service
public class LedgerService {

    @Autowired
    LedgerRepository ledgerRepository;

    public List<Ledger> getAllLedgers() {
        return ledgerRepository.findAll();
    }

    public Optional<Ledger> findLedgerById(Long id) {
        return ledgerRepository.findById(id);
    }

    public Ledger createOrUpdateLedger(Ledger ledger) {
        return ledgerRepository.save(ledger);
    }

    public ResponseEntity<?> deletePurchase(Ledger ledger){
        ledgerRepository.delete(ledger);
        return ResponseEntity.ok().build();
    }

}
