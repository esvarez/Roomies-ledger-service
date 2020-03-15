package dev.ericksuarez.roomies.ledger.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ericksuarez.roomies.ledger.service.model.Ledger;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, Long> {
}
