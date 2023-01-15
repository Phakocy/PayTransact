package com.interswitch.paytransact.entities;

import com.interswitch.paytransact.entities.commons.BaseEntity;
import com.interswitch.paytransact.entities.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Transactions")
public class Transaction extends BaseEntity {
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;
    private BigDecimal amount;
    private BigDecimal balance;
    private String reason;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
