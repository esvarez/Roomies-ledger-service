package dev.ericksuarez.roomies.ledger.service.model.dto;

import dev.ericksuarez.roomies.ledger.service.model.entity.Purchase;
import dev.ericksuarez.roomies.ledger.service.model.entity.relations.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PurchaseDto {
    private User userBuyer;

    private Set<User> usersToSplit;

    @Positive
    @NotNull(message = "Provide the price of your purchase")
    private BigDecimal price;

    @NotNull(message = "Provide a description or meaningful name")
    private String description;

    private String photo;

    @NotNull
    private LocalDate date;
}
