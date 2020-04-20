package dev.ericksuarez.roomies.ledger.service;

import dev.ericksuarez.roomies.ledger.service.model.entity.relations.Unit;
import dev.ericksuarez.roomies.ledger.service.repository.LedgerRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public class TestUtil {

    @Autowired
    private static TestEntityManager testEntityManager;

    @Autowired
    private LedgerRepository ledgerRepository;

    public static void setUp() {
        val house = Unit.builder().build();

        //this.testEntityManager.persist(house);
    }
}
