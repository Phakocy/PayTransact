package com.interswitch.paytransact.entities;

import com.interswitch.paytransact.entities.commons.BaseEntity;
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
@Table(name = "Accounts")
public class Account extends BaseEntity {
    private Double balance;
    @Column(
            name = "card_number",
            nullable = false,
            unique = true
    )
    private Long cardNumber;
    @Column(
            name = "account_number",
            nullable = false,
            unique = true
    )
    private Long accountNumber;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
