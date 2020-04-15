package dev.ericksuarez.roomies.ledger.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ericksuarez.roomies.ledger.service.model.entity.relations.House;

public interface HouseRepository extends JpaRepository<House, Long> {
}
