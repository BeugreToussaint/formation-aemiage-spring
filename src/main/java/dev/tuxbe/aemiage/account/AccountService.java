package dev.tuxbe.aemiage.account;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account);
    AccountDto updateAccount(String accountNumber, AccountDto account);
    void deleteAccount(String accountNumber);
    AccountDto getBalance(String accountNumber);
    List<AccountDto> getAccounts();
    AccountDto deposit(String accountNumber, double amount);
    AccountDto withdraw(String accountNumber, double amount);

}
