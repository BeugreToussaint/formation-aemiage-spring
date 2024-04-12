package dev.tuxbe.aemiage.transaction;

import dev.tuxbe.aemiage.model.ResponseApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("transactions")
@Tag(name = "Transactions", description = "description API transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("")
    @Operation(summary = "Liste transactions")
    public ResponseApi getTransactions(){
        return new ResponseApi(false, "Traitement OK !", this.transactionService.getTransactions());
    }

    @GetMapping("/paginate")
    @Operation(summary = "Liste transactions paginate")
    public ResponseApi getTransactionsPaginate(@RequestParam int page, @RequestParam int size){
        return new ResponseApi(false, "Traitement OK !", this.transactionService.getTransactionsPaginated(page, size));
    }

    @GetMapping("/{transactionId}")
    @Operation(summary = "get transactions par uuid")
    public ResponseApi getTransaction(@PathVariable UUID transactionId){
        return new ResponseApi(false, "Traitemnt Ok !", this.transactionService.getTransaction(transactionId));
    }
    @GetMapping("/{accountNumber}")
    @Operation(summary = "get transactions par accountNumber")
    public ResponseApi getTransactionByAccountNumber(@PathVariable String accountNumber){
        return new ResponseApi(false, "Traitemnt Ok !", transactionService.getTransactionByAccountNumber(accountNumber));
    }

}
