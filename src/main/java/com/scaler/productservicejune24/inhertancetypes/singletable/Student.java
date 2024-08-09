package com.scaler.productservicejune24.inhertancetypes.singletable;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Getter
@Setter
@Entity
 @DiscriminatorValue(value="3")
public class Student extends User {
    private String batch;
}
