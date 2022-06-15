package com.gemin.geminaccountservice.entity;


import com.gemin.geminaccountservice.constants.enums.TransactionStatus;
import com.gemin.geminaccountservice.constants.enums.TransactionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "transactions")
public class Transaction extends BaseEntity{

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne()
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "transaction_id", nullable = false )
    private String transactionId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "sender")
    private String sender;

    @Column(name = "sender_account_number")
    private long senderAccountNumber;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "receiver_account_number")
    private long receiverAccountNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "transaction_status")
    @Builder.Default
    private TransactionStatus transactionStatus = TransactionStatus.PENDING;

}
