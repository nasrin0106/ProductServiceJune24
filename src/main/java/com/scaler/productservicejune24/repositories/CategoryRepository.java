package com.scaler.productservicejune24.repositories;

//import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import com.scaler.productservicejune24.models.category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<category, Long> {
           category save(category Category);
}
