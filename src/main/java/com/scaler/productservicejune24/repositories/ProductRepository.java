package com.scaler.productservicejune24.repositories;

import com.scaler.productservicejune24.models.product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.projections.ProductWithIdAndTitle;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<product,Long>{
    //product repository should contain all the methods (CRUD) related  to product model.

    List<product>findByPriceIsGreaterThan(Double price);
    //select * from products where price>?

    List<product>findProductByTitleLike(String word);
    //select * from products where title like %iphone%
    Optional <product> findById(Long id);

    @Override
    Page<product> findAll(Pageable pageable);
    //HQL
//    @Query("select p.id,p.title from product p")
//   List< ProductWithIdAndTitle> randomSearchMethod();

}
