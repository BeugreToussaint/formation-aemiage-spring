package dev.tuxbe.aemiage.producer;

import dev.tuxbe.aemiage.account.AccountDto;
import dev.tuxbe.aemiage.events.CreateTransactionEvent;
import dev.tuxbe.aemiage.exception.GlobalException;
import dev.tuxbe.aemiage.model.TypeOperation;
import dev.tuxbe.aemiage.transaction.Transaction;
import dev.tuxbe.aemiage.transaction.TransactionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;

@Component
public class TransactionListener {

    private final static Logger log = LoggerFactory.getLogger(GlobalException.class);

    private final TransactionServiceImpl transactionService;

    public TransactionListener(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    @EventListener(condition = "#createTransactionEvent.name eq 'deposit'")
    public void handleDebitAccount(CreateTransactionEvent createTransactionEvent){
        AccountDto source = (AccountDto) createTransactionEvent.getSource();
        Transaction transaction = this.transactionService.createTransaction(new Transaction(null, TypeOperation.DEPOSIT, source.amount(), source.accountNumber(), LocalDateTime.now()));
        log.info("Transaction created: {}", transaction);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    @EventListener(condition = "#createTransactionEvent.name eq 'withdraw'")
    public void handleCreditAccount(CreateTransactionEvent createTransactionEvent){
        AccountDto source = (AccountDto) createTransactionEvent.getSource();
        Transaction transaction = this.transactionService.createTransaction(new Transaction(null, TypeOperation.WITHDRAW, source.amount(), source.accountNumber(), LocalDateTime.now()));
        log.info("Transaction created: {}", transaction);
    }

}
