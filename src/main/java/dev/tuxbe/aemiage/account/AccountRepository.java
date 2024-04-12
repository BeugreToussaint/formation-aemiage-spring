package dev.tuxbe.aemiage.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByAccountNumber(String accountnumber);
    void deleteByAccountNumber(String accountnumber);
}
