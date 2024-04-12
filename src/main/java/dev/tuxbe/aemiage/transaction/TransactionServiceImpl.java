package dev.tuxbe.aemiage.transaction;

import dev.tuxbe.aemiage.account.AccountRepository;
import dev.tuxbe.aemiage.exception.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getTransactions() {
        return this.transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsPaginated(int page, int size) {
        return this.transactionRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public List<Transaction> getTransactionByAccountNumber(String accountNumber) {
        return this.transactionRepository.findByAccountTransaction(accountNumber);
    }

    @Override
    public Transaction getTransaction(UUID transactionId) {
        return this.transactionRepository.findById(transactionId).orElseThrow(()-> new ResourceNotFoundException("Transaction not found"));
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }
}
