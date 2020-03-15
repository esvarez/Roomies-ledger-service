package dev.ericksuarez.roomies.ledger.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ericksuarez.roomies.ledger.service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
