package com.interswitch.paytransact.entities.commons;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {
    private Integer id;
    private Date dateCreated;
}
