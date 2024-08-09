package com.scaler.productservicejune24.models;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

import java.util.List;

@Getter
@Setter
@Entity
public class category extends BaseModel{
    private String name;
    private String description;
//    @OneToMany(mappedBy ="Category")
//    private List<product> Products;
}
 