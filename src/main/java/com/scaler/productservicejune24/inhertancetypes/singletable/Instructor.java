package com.scaler.productservicejune24.inhertancetypes.singletable;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Getter
@Setter
@Entity(name="st_Instructor")
@DiscriminatorValue(value="1")
public class Instructor extends User {
    private String company;
}
 