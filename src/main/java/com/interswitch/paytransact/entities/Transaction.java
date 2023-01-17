package com.interswitch.paytransact.entities;

import com.interswitch.paytransact.entities.commons.BaseEntity;
import com.interswitch.paytransact.entities.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Transactions")
public class Transaction extends BaseEntity {
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;
    private Double amount;
    private Double balance;
    private String narration;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
