package dev.ericksuarez.roomies.ledger.service.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.ericksuarez.roomies.ledger.service.model.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("SELECT p FROM Purchase p WHERE p.user.unit.id = :unitId")
    List<Purchase> getAllByUnitId(@Param("unitId") Long unitId);

    @Query("SELECT p FROM Purchase p WHERE p.user.unit.id = :unitId AND p.date BETWEEN :starDate AND :endDate ORDER BY p.date ASC")
    List<Purchase> getAllByUnitIdAndDate(@Param("unitId") Long unitId,
                                         @Param("starDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);

    @Query("SELECT p from Purchase  p WHERE p.user.id = :userId")
    List<Purchase> getAllByUserId(@Param("userId") UUID userId);

}
