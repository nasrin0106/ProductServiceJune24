package com.scaler.productservicejune24.inhertancetypes.tableperclass;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

@Getter
@Setter
@Entity(name="tbc_Instructor")
public class Instructor extends User {
    private String company;
}
 