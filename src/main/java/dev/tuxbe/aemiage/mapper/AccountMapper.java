package dev.tuxbe.aemiage.mapper;

import dev.tuxbe.aemiage.account.AccountEntity;
import dev.tuxbe.aemiage.account.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDto(AccountEntity accountEntity);
    AccountEntity toAccount(AccountDto accountDto);

}
