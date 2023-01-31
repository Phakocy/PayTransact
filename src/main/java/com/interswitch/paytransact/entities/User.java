package com.interswitch.paytransact.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interswitch.paytransact.entities.commons.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User extends BaseEntity {
    @Column(
            nullable = false,
            unique = true
    )
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public User(int id, String email, LocalDate dateCreated) {
    }
}
