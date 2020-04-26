package dev.ericksuarez.roomies.ledger.service.model.entity.relations;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.ericksuarez.roomies.ledger.service.model.entity.Account;
import dev.ericksuarez.roomies.ledger.service.model.entity.AuditModel;
import dev.ericksuarez.roomies.ledger.service.model.entity.Purchase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private UUID id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Unit unit;

    private String name;
/*
    private String username;

    private Boolean active = true;

    @OneToMany
    @JsonIgnore
    private Set<Purchase> purchases;
*/
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "users_accounts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> accounts = new HashSet<>();

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("User=(id=%s, unit=%s, accounts=", this.id,
                this.unit==null
                        ? "null"
                        : this.unit.getId()
                ));
        if (this.accounts == null){
            stringBuilder.append("null");
        } else {
            this.accounts.forEach((account -> {
                stringBuilder.append(String.format("Account=(id=%s) ", account.getId()));
            }));
        }
        return stringBuilder.toString();
    }
}
