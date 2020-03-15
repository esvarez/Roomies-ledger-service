package dev.ericksuarez.roomies.ledger.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.ericksuarez.roomies.ledger.service.model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
/*
    @Query("SELECT p from Purchase  p WHERE p.user.id = :id")
    List<Purchase> getAllByUserId(@Param("id") Long userId);

    @Query("SELECT p FROM Purchase p WHERE p.user.houseId = :id")
    List<Purchase> getAllByHouseId(@Param("id") Long houseId);
 */
}
