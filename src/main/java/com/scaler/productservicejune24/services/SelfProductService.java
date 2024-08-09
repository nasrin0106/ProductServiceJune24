package com.scaler.productservicejune24.services;


import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import com.scaler.productservicejune24.models.product;
import com.scaler.productservicejune24.models.category;
import com.scaler.productservicejune24.repositories.ProductRepository;
import com.scaler.productservicejune24.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductService implements ProductService {
        private ProductRepository productRepository;
        private CategoryRepository categoryRepository;
        public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
            this.productRepository=productRepository;
            this.categoryRepository=categoryRepository;
        }
            @Override
    public product getSingleProduct(Long productId) throws ProductNotFoundException {
                //Make a call to DB to fetch a product with given id
                Optional<product> productOptional = productRepository.findById(productId);
//                COWArrayList<Object> productOptional;s
                if (productOptional.isEmpty()) {
                    throw new ProductNotFoundException("Product with id: " + productId + " doesn't exist");
                }

//                product Product = productOptional.get();
                return productOptional.get();

            }
    @Override
    public Page<product>getAllProducts(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber,pageSize, Sort.by("price").ascending()));
    }
     //patch_partial update
    @Override
    public product updateProduct(Long id, product Product) throws ProductNotFoundException {
            Optional<product> optionalProduct= productRepository.findById(id);
            if (optionalProduct.isEmpty()) {
                throw new ProductNotFoundException("Product with id : "+id+"doesn't exist");
            };
            product productInDB=optionalProduct.get();
            if(Product.getTitle()!=null){
                productInDB.setTitle(Product.getTitle());
            }
            if(Product.getPrice()!=null){
                   productInDB.setPrice(Product.getPrice());
            }
            return productRepository.save(productInDB);
    }
    //put_replace
    @Override
    public product replaceProduct(Long id, product Product)throws ProductNotFoundException {
            Optional<product> optionalProduct= productRepository.findById(id);
            if (optionalProduct.isEmpty()) {
                throw new ProductNotFoundException("Product with id : "+id+"doesn't exist");
            };
            product productInDB=optionalProduct.get();
            productInDB.setTitle(Product.getTitle());
            productInDB.setPrice(Product.getPrice());
            return productRepository.save(productInDB);

    }

    @Override
    public void deleteProduct(Long id) {
          productRepository.deleteById(id);
    }

    @Override
    public product addNewProduct(product Product) {
            category Category =Product.getCategory();
//            if(Category.getId()==null){
//                //we need to create a new category object first
//             Category=CategoryRepository.save(Category);
//             Product.setCategory(Category);
//            }
         return  productRepository.save(Product);
    }
}
