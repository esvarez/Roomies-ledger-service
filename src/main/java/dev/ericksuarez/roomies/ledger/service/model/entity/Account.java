package dev.ericksuarez.roomies.ledger.service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.ericksuarez.roomies.ledger.service.model.entity.relations.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "accounts")
public class Account extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "accounts")
    private Set<User> users  = new HashSet<>();

    @JsonIgnore
    @Type(type="uuid-char")
    private UUID userCred;

    @JsonIgnore
    @Type(type="uuid-char")
    private UUID userDebt;

    @Column(name = "amount_debt")
    private BigDecimal amountDebt;
}
