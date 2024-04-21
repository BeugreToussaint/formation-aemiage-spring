package dev.tuxbe.aemiage.account;

import dev.tuxbe.aemiage.events.CreateTransactionEvent;
import dev.tuxbe.aemiage.exception.AccountAlreadyExistException;
import dev.tuxbe.aemiage.exception.ResourceNotFoundException;
import dev.tuxbe.aemiage.exception.TypeAccountDontExistException;
import dev.tuxbe.aemiage.mapper.AccountMapper;
import dev.tuxbe.aemiage.contract.TypeAccount;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, ApplicationEventPublisher applicationEventPublisher) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    @Override
    public AccountDto createAccount(AccountDto accountDto) throws AccountAlreadyExistException {
        if (verifyAccount(accountDto.accountNumber())) {
            throw new AccountAlreadyExistException("Account ["+accountDto.accountNumber()+"] already exists");
        }

        if (!verifyTypeAccount(accountDto.typeAccount())) {
            throw new TypeAccountDontExistException("Type account ["+accountDto.typeAccount()+"] does not exist. Type account available [SAVED, CURRENT]");
        }

        AccountEntity accountEntitySaved = this.accountRepository.save(accountMapper.toAccount(accountDto));
        this.applicationEventPublisher.publishEvent(new CreateTransactionEvent(accountDto, "deposit"));
        return accountMapper.toDto(accountEntitySaved);
    }

    @Transactional
    @Override
    public AccountDto updateAccount(String accountNumber, AccountDto account) {


        AccountEntity byAccountNumber = this.accountRepository.findByAccountNumber(account.accountNumber()).orElseThrow(()-> new ResourceNotFoundException("Account ["+accountNumber+"] does not exist"));
        if (!verifyTypeAccount(account.typeAccount())) {
            throw new TypeAccountDontExistException("Type account ["+account.typeAccount()+"] does not exist. Type account available [SAVED, CURRENT]");
        }

        BeanUtils.copyProperties(account, byAccountNumber);
        AccountEntity accountEntitySaved = this.accountRepository.save(byAccountNumber);
        return accountMapper.toDto(accountEntitySaved);
    }

    @Transactional
    @Override
    public void deleteAccount(String accountNumber) {
        if (!verifyAccount(accountNumber)) {
            throw new ResourceNotFoundException("Account ["+accountNumber+"] does not exist");
        }
        this.accountRepository.deleteByAccountNumber(accountNumber);
    }

    @Override
    public AccountDto getBalance(String accountNumber) {
        if (!verifyAccount(accountNumber)) {
            throw new ResourceNotFoundException("Account ["+accountNumber+"] does not exist");
        }
        return accountMapper.toDto(this.accountRepository.findByAccountNumber(accountNumber).get());
    }

    @Override
    public List<AccountDto> getAccounts() {
        return this.accountRepository.findAll().parallelStream().map(accountMapper::toDto).toList();
    }

    @Transactional
    @Override
    public AccountDto deposit(String accountNumber, double amount) {
        AccountEntity accountEntity = this.accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new ResourceNotFoundException("Account [" + accountNumber + "] does not exist"));
        AccountEntity oldvalue = new AccountEntity();
        BeanUtils.copyProperties(accountEntity, oldvalue);
        oldvalue.setAmount(amount);
        accountEntity.setAmount(accountEntity.getAmount()+amount);
        AccountDto accountMapperDto = accountMapper.toDto(this.accountRepository.save(accountEntity));
        this.applicationEventPublisher.publishEvent(new CreateTransactionEvent(accountMapper.toDto(oldvalue), "deposit"));
        return accountMapperDto;
    }

    @Transactional
    @Override
    public AccountDto withdraw(String accountNumber, double amount) {
        AccountEntity accountEntity = this.accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new ResourceNotFoundException("Account [" + accountNumber + "] does not exist"));
        AccountEntity oldvalue = new AccountEntity();
        BeanUtils.copyProperties(accountEntity, oldvalue);
        oldvalue.setAmount(amount);
        if(accountEntity.getAmount() - amount < 0){
            throw new RuntimeException("Account [" + accountNumber + "] does not have enough money");
        }
        accountEntity.setAmount(accountEntity.getAmount()-amount);
        AccountDto accountMapperDto = accountMapper.toDto(this.accountRepository.save(accountEntity));
        this.applicationEventPublisher.publishEvent(new CreateTransactionEvent(accountMapper.toDto(oldvalue), "withdraw"));
        return accountMapperDto;
    }

    public boolean verifyAccount(String accountNumber) {
        Optional<AccountEntity> byAccountNumber = this.accountRepository.findByAccountNumber(accountNumber);
        return byAccountNumber.isPresent();
    }

    public boolean verifyTypeAccount(String typeAccount){
        return Arrays.stream(TypeAccount.values()).map(TypeAccount::toString).toList().contains(typeAccount);
    }

}
