package com.gemin.geminaccountservice.entity;

import com.gemin.geminaccountservice.constants.enums.AccountCurrency;
import com.gemin.geminaccountservice.constants.enums.AccountType;
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
@Table(name = "accounts",
        indexes = @Index(name = "account_num_index", columnList = "account_number"),
        uniqueConstraints= @UniqueConstraint(columnNames = "account_number")
)
public class Account extends BaseEntity{

    @Column(name = "account_number", length = 10)
    private long accountNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_type", length = 10)
    @Builder.Default
    private AccountType accountType = AccountType.SAVINGS;

    @Column(name = "account_balance")
    @Builder.Default
    private BigDecimal accountBalance = BigDecimal.valueOf(0.0);

    @Column(name = "account_pin", length = 4)
    private String accountPin;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_currency")
    @Builder.Default
    private AccountCurrency accountCurrency = AccountCurrency.EUR;

    @Column(name = "activated")
    private boolean isActivated = false;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

}