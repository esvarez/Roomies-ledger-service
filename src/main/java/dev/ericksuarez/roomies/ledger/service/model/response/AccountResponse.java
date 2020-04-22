package dev.ericksuarez.roomies.ledger.service.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AccountResponse {
    private Long accountId;

    private String userName;

    private BigDecimal amount;
}
