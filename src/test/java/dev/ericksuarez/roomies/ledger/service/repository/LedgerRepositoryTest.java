package dev.ericksuarez.roomies.ledger.service.repository;

import dev.ericksuarez.roomies.ledger.service.model.entity.relations.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class LedgerRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private LedgerRepository ledgerRepository;

    @Test
    public void foo(){
        Set<String> x = new HashSet<>();
        x.add("que");
        x.add("tu");
        Set<User> users = new HashSet<>();

        User user = User.builder().id(UUID.randomUUID()).build();
        /*
        Set<String> cadena = new HashSet<>();
        cadena.add("hola");
        cadena.add("que");
        cadena.add("tal");

        user.setCadenas(cadena);
        users.add(user);
        user = new User();
        cadena = new HashSet<>();
        cadena.add("estas");
        cadena.add("tu");
        user.setCadenas(cadena);*/


        users.add(user);


        users.stream()
                //.map(u -> u.getCadenas().retainAll(x))
                .peek((us)-> {
                    System.out.println("***inicio****");
                    System.out.println(us.getId());
                    System.out.println("****fin inicio****");
                })
                .peek((us)-> {
                    System.out.println("-----filtro--------");
                    //System.out.println(us.getCadenas());
                    System.out.println("-----fin filtro-----");
                })
                //.map((cadena)->cadena.toUpperCase())
                .peek((us)-> {
                    System.out.println(">>>>>>mayusculas>>>>>>");
                    System.out.println(us);
                    System.out.println(">>>>>>>fin mayusculas>>>>>>");
                }).forEach(System.out::println);
    }

    @Test
    public void doo(){
        User user = new User();
        /*user.setAmount(BigDecimal.TEN);
        user.setAmount(user.getAmount().add(BigDecimal.ONE));
        user.setAmount(user.getAmount().subtract(BigDecimal.TEN));*/
        System.out.println("End");
    }
}
