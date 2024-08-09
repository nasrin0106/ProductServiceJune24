package com.scaler.productservicejune24.inhertancetypes.joinedtable;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@Entity(name="jt_student")
@PrimaryKeyJoinColumn(name="user_id")
public class Student extends User {
    private String batch;
}
