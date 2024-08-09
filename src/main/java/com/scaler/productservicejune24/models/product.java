package com.scaler.productservicejune24.models;

import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@Entity

public class product extends BaseModel {
    private String title;
    private Double price;
//    @ManyToOne(cascade= CascadeType.PERSIST)
    @ManyToOne
    private category Category;

}
