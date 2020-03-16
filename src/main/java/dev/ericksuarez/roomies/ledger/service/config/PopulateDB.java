package dev.ericksuarez.roomies.ledger.service.config;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.ericksuarez.roomies.ledger.service.model.House;
import dev.ericksuarez.roomies.ledger.service.model.Purchase;
import dev.ericksuarez.roomies.ledger.service.model.User;
import dev.ericksuarez.roomies.ledger.service.repository.HouseRepository;
import dev.ericksuarez.roomies.ledger.service.repository.UserRepository;
import dev.ericksuarez.roomies.ledger.service.service.PurchaseService;

@Configuration
public class PopulateDB {

    @Bean
    public CommandLineRunner initDataBase(UserRepository userRepository, HouseRepository houseRepository, PurchaseService purchaseService){
        return args -> {
            House house = House.builder().build();
            House house2 = House.builder().build();
            houseRepository.save(house);
            houseRepository.save(house2);
            User user = User.builder().name("Erick").house(house).build();
            User user1 = User.builder().name("Erick").house(house).build();
            User user2 = User.builder().name("Erick").house(house2).build();
            userRepository.save(user);
            userRepository.save(user1);
            userRepository.save(user2);
            Purchase purchase = Purchase.builder().user(user).description("uno").price(BigDecimal.ONE).date(LocalDate.now()).build();
            purchase.setCreatedAt(Date.from(Instant.now()));
            purchase.setUpdatedAt(Date.from(Instant.now()));
            Purchase purchase1 = Purchase.builder().user(user1).description("dos").price(BigDecimal.TEN).date(LocalDate.now()).build();
            purchase1.setCreatedAt(Date.from(Instant.now()));
            purchase1.setUpdatedAt(Date.from(Instant.now()));
            Purchase purchase2 = Purchase.builder().user(user2).description("dos").price(BigDecimal.ONE).date(LocalDate.now()).build();
            purchase2.setCreatedAt(Date.from(Instant.now()));
            purchase2.setUpdatedAt(Date.from(Instant.now()));
            purchaseService.createOrUpdatePurchase(purchase);
            purchaseService.createOrUpdatePurchase(purchase1);
            purchaseService.createOrUpdatePurchase(purchase2);
        };
    }
}
