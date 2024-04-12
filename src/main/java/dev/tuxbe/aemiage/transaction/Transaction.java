package dev.tuxbe.aemiage.transaction;

import dev.tuxbe.aemiage.model.TypeOperation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
    private double amount;
    @Pattern( regexp = "\\d{12}", message = "Account number must be 12-digit caractere")
    private String accountTransaction;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime datetransaction;

    public Transaction() {}

    public Transaction(UUID id, TypeOperation typeOperation, double amount, String accountTransaction, LocalDateTime datetransaction) {
        this.id = id;
        this.typeOperation = typeOperation;
        this.amount = amount;
        this.accountTransaction = accountTransaction;
        this.datetransaction = datetransaction;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountTransaction() {
        return accountTransaction;
    }

    public void setAccountTransaction(String accountTransaction) {
        this.accountTransaction = accountTransaction;
    }

    public LocalDateTime getDatetransaction() {
        return datetransaction;
    }

    public void setDatetransaction(LocalDateTime datetransaction) {
        this.datetransaction = datetransaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(id, that.id) && typeOperation == that.typeOperation && Objects.equals(accountTransaction, that.accountTransaction) && Objects.equals(datetransaction, that.datetransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeOperation, amount, accountTransaction, datetransaction);
    }
}
