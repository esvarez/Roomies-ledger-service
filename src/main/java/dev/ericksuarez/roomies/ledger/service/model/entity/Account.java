package dev.ericksuarez.roomies.ledger.service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.ericksuarez.roomies.ledger.service.model.entity.relations.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "accounts")
public class Account extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "users_accounts",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> users  = new HashSet<>();

    @JsonIgnore
    @Type(type="uuid-char")
    private UUID userCred;

    @JsonIgnore
    @Type(type="uuid-char")
    private UUID userDebt;

    @Column(name = "amount_debt")
    private BigDecimal amountDebt;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Account=(id=%d, users=", this.id));
        if (this.users == null){
            stringBuilder.append("null");
        } else {
            this.users.forEach((user -> {
                stringBuilder.append(String.format("User=(id=%s) ", user.getId().toString()));
            }));
        }
        stringBuilder.append(String.format("userCred=%s, userDebt=%s, amountDebt=%s)", this.userCred, this.userDebt, this.amountDebt));
        return stringBuilder.toString();
    }

}
