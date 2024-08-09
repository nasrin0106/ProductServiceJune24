package com.scaler.productservicejune24.models;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel implements Serializable {
      @Id//primary key
      @GeneratedValue(strategy= GenerationType.IDENTITY)//auto increment value
      private Long id;
      private Date createdAt;
      private Date updatedAt;
}
