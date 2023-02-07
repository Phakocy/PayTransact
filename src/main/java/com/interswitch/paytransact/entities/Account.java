package com.interswitch.paytransact.entities;

import com.interswitch.paytransact.entities.commons.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Accounts")
public class Account extends BaseEntity {
    private Double balance;
    private Long cardNumber;
    private Long accountNumber;
    private Integer user;
}
