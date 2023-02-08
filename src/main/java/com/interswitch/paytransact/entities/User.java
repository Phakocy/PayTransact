package com.interswitch.paytransact.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interswitch.paytransact.entities.commons.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public User(int id, String email, LocalDate dateCreated) {
    }
}
