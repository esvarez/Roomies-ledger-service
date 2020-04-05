package dev.ericksuarez.roomies.ledger.service.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class LedgerRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private LedgerRepository ledgerRepository;

    @Test
    public void foo(){
        System.out.println("Foo");
    }

}
