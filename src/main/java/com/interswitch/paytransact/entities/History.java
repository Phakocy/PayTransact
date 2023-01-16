package com.interswitch.paytransact.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interswitch.paytransact.entities.commons.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Account_History")
public class History extends BaseEntity {
    private String body;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Account account;
}
