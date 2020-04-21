package dev.ericksuarez.roomies.ledger.service.repository;

import dev.ericksuarez.roomies.ledger.service.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Set<Account> findAccountByUsersId(UUID id);

    Set<Account> findAccountsByUsersId(UUID id);
}
