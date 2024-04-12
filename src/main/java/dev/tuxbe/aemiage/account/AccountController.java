package dev.tuxbe.aemiage.account;

import dev.tuxbe.aemiage.model.ResponseApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
@Tag(name = "Accounts", description = "description API account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Liste des comptes")
    public ResponseApi getAccounts(){
        return new ResponseApi(false,"Traitement ok !",this.accountService.getAccounts());
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Création d'un compte")
    public ResponseApi create(@RequestBody AccountDto accountDto){
        return new ResponseApi(false, "Account created with success" ,this.accountService.createAccount(accountDto));
    }
    @PutMapping("/{accountnumber}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Modification d'un compte")
    public ResponseApi update(@PathVariable String accountnumber, @RequestBody AccountDto accountDto){
        return new ResponseApi(false, "Account updated success", this.accountService.updateAccount(accountnumber, accountDto));
    }
    @DeleteMapping("/{accountnumber}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Suppression d'un compte")
    public ResponseApi delete(@PathVariable String accountnumber){
        this.accountService.deleteAccount(accountnumber);
        return new ResponseApi(false, "Account deleted success", null);
    }
    @GetMapping("/{accountnumber}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Récupération d'un compte par son numéro")
    public ResponseApi getBalance(@PathVariable String accountnumber) {
        return new ResponseApi(false, "Traitement OK" ,this.accountService.getBalance(accountnumber));
    }
    @PatchMapping("/deposit")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Crédit un compte")
    public ResponseApi deposit(@RequestParam String accountnumber, @RequestParam double amount) {
        return new ResponseApi(false, "Account deposited success", this.accountService.deposit(accountnumber, amount));
    }
    @PatchMapping("/withdrawal")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Débiter un compte")
    public ResponseApi withdrawal(@RequestParam String accountnumber, @RequestParam double amount) {
        return new ResponseApi(false, "Account withdrawal success !", this.accountService.withdraw(accountnumber, amount));
    }


}
