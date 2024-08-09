package com.scaler.productservicejune24.inhertancetypes.joinedtable;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Getter
@Setter
@Entity(name="jt_user")
@Inheritance(strategy = InheritanceType.JOINED)

public class User {
    @Id
    private long id;
    private String name;
    private String email;
}
