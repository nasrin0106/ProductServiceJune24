package com.scaler.productservicejune24.inhertancetypes.singletable;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Getter
@Setter
@Entity(name="st_Mentor")
@DiscriminatorValue(value="2")
public class Mentor extends User {
    private String company;
}

