package com.gemin.geminaccountservice.repository;

import com.gemin.geminaccountservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
