package dev.ericksuarez.roomies.ledger.service.facade;

import dev.ericksuarez.roomies.ledger.service.model.dto.PurchaseDto;
import dev.ericksuarez.roomies.ledger.service.model.entity.relations.User;
import dev.ericksuarez.roomies.ledger.service.repository.UserRepository;
import dev.ericksuarez.roomies.ledger.service.service.AccountService;
import dev.ericksuarez.roomies.ledger.service.service.PurchaseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class AccountFacadeTest {

    @Autowired
    private PurchaseFacade accountFacade;

    @Mock
    private AccountService accountService;

    @Mock
    private PurchaseService purchaseService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        this.accountFacade = new PurchaseFacade(accountService, purchaseService, userRepository);
    }

    @Test
    public void foo() {
        Set<User> users = new HashSet<>();
        users.add(User.builder()
                .id(UUID.randomUUID())
                .build()
        );
        users.add(User.builder()
                .id(UUID.randomUUID())
                .build()
        );
        PurchaseDto purchaseDto = PurchaseDto.builder()
                .date(LocalDate.now())
                .description("String")
                .price(BigDecimal.TEN)
                .userBuyer(
                        User.builder()
                        .id(UUID.randomUUID())
                        .build()
                ).usersToSplit(users)
                .build();

        accountFacade.transactPurchase(purchaseDto);
    }

    @Test
    public void doo(){
        BigDecimal x = BigDecimal.TEN;


        System.out.println(x);

        System.out.println(x.negate());
    }
}
