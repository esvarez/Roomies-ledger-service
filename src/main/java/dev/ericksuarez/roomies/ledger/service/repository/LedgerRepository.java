package dev.ericksuarez.roomies.ledger.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ericksuarez.roomies.ledger.service.model.entity.Ledger;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, Long> {
    List<Ledger> getAllByUserId(Long userId);
}
