package dev.tuxbe.aemiage.account;

import dev.tuxbe.aemiage.contract.TypeAccount;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @Pattern(regexp = "\\d{12}", message = "Account number must be 12-digit caractere")
    private String accountNumber;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TypeAccount typeAccount;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime datecreation;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime datemodification;


    public AccountEntity() {
    }

    public AccountEntity(Long id, String accountNumber, double amount, TypeAccount typeAccount, LocalDateTime datecreation, LocalDateTime datemodification) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.typeAccount = typeAccount;
        this.datecreation = datecreation;
        this.datemodification = datemodification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(TypeAccount typeAccount) {
        this.typeAccount = typeAccount;
    }

    public LocalDateTime getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDateTime datecreation) {
        this.datecreation = datecreation;
    }

    public LocalDateTime getDatemodification() {
        return datemodification;
    }

    public void setDatemodification(LocalDateTime datemodification) {
        this.datemodification = datemodification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(id, that.id) && Objects.equals(accountNumber, that.accountNumber) && typeAccount == that.typeAccount && Objects.equals(datecreation, that.datecreation) && Objects.equals(datemodification, that.datemodification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, amount, typeAccount, datecreation, datemodification);
    }

    @PostConstruct
    public void init() {
        datecreation = LocalDateTime.now();
    }
}
