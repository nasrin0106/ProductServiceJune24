package com.scaler.productservicejune24.services;
import com.scaler.productservicejune24.models.product;
import java.util.List;
import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;


import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public interface ProductService {
    product getSingleProduct(Long productId) throws ProductNotFoundException;
    Page<product> getAllProducts(int pageNumber, int pageSize);
    product updateProduct(Long id,product Product) throws ProductNotFoundException;
    product replaceProduct(Long id,product Product) throws ProductNotFoundException;
    void deleteProduct(Long id);
    product addNewProduct(product Product);
}

