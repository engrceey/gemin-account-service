package com.gemin.geminaccountservice.repository;

import com.gemin.geminaccountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> getAccountByAccountNumber(long accountNumber);
}
