package dev.ericksuarez.roomies.ledger.service.repository;

import dev.ericksuarez.roomies.ledger.service.model.entity.relations.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
