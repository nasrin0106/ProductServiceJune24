package com.scaler.productservicejune24.controllers;
import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import com.scaler.productservicejune24.models.product;
import com.scaler.productservicejune24.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {
    private ProductService productService;
    public ProductController(@Qualifier("FakeStoreProductService")ProductService productService){
            this.productService=productService;
    }
    //http://localhost:8080/products/10
    @GetMapping("/{id}")
    public ResponseEntity<product> getProductById(@PathVariable("id")Long id) throws ProductNotFoundException {

        ResponseEntity<product> response=new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );
//        return productService.getSingleProduct(id);
        return response;
    }
    @GetMapping()

    public Page<product> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return productService.getAllProducts(pageNumber, pageSize);
    }

    @DeleteMapping({"id"})
    public void deleteProduct(@PathVariable("id") Long productId){
            productService.deleteProduct(productId);
    }
    //PATCH->http://localhost:8080/products/1
    @PatchMapping({"id"})
    public product updateProduct(@PathVariable("id")Long id, @RequestBody product Product) throws ProductNotFoundException {

        return productService.updateProduct(id,Product);
    }

    @PutMapping({"id"})
    public product replaceProduct(@PathVariable("id")Long id, @RequestBody product Product) throws ProductNotFoundException {
        return productService.replaceProduct(id,Product);
    }

    @PostMapping
    public product addNewProduct(@RequestBody product Product){
        return productService.addNewProduct(Product);
    }
}
