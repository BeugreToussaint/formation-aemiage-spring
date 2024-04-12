package dev.tuxbe.aemiage.transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    List<Transaction> getTransactions();
    List<Transaction> getTransactionsPaginated(int page, int size);
    List<Transaction> getTransactionByAccountNumber(String accountNumber);
    Transaction getTransaction(UUID transactionId);
    Transaction createTransaction(Transaction transaction);
}
