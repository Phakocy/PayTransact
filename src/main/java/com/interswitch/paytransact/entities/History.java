package com.interswitch.paytransact.entities;

import com.interswitch.paytransact.entities.commons.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class History extends BaseEntity {
    private String body;
    private Integer account;
}
