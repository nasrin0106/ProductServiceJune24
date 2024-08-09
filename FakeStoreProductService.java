package com.scaler.productservicejune24.services;

import com.scaler.productservicejune24.dtos.FakeStoreProductDto;
import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import com.scaler.productservicejune24.models.category;
import com.scaler.productservicejune24.models.product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.List;
import java.util.ArrayList;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private final ResourceUrlProvider url;
    private RestTemplate restTemplate;
    private RedisTemplate<String,Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, ResourceUrlProvider url,RedisTemplate redisTemplate){
           this.restTemplate=restTemplate;
        this.url = url;
        this.redisTemplate=redisTemplate;
    }

    @Override
    public product getSingleProduct(Long productId)throws ProductNotFoundException {

        String url="http://fakestoreapi.com/products/"+productId;

        //Try to fetch the product from redis
        product Product=(product)redisTemplate.opsForHash().get("PRODUCTS","PRODUCT"+productId);
        if(Product!=null){
            //CACHE HIT
            return Product;
        }
//Call FakeStore to fetch the product with given id.=?http call
// CACHE MISS
        FakeStoreProductDto fakeStoreProductDto= restTemplate.getForObject(url,FakeStoreProductDto.class);
//        FakeStoreProductDto fakeStoreProductDto= restTemplate.getForObject(url:"https://fakestoreapi.com/products/"+productId,
//                FakeStoreProductDto.class);

         Product= convertFakeStoreProductToProduct(fakeStoreProductDto);
//Store the product in redis
         redisTemplate.opsForHash().put("PRODUCTS","PRODUCT"+productId,Product);
         return Product;
    }

     private product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto){
         //  convert FakeStoreProductDto in to Product.
         product Product=new product();
         Product.setId(fakeStoreProductDto.getId());
         Product.setTitle(fakeStoreProductDto.getTitle());
         Product.setPrice(fakeStoreProductDto.getPrice());
         category Category=new category();
         Category.setDescription(fakeStoreProductDto.getCategory());
         Product.setCategory(Category);


         return Product;
     }
    @Override
    public Page<product> getAllProducts(int pageNumber,int pageSize) {
        String url="http://fakestoreapi.com/products/";
        FakeStoreProductDto[] fakeStoreProductDtos= restTemplate.getForObject(url,FakeStoreProductDto[].class);

        //convert list of FakeStoreProductDto into list of product
        List<product> Products=new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){
            Products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
        }

        return new PageImpl<>(Products);
    }
    //Partial Update
    @Override
    public product updateProduct(Long id,product Product){
        String url="http://fakestoreapi.com/products/";
        //put
        RequestCallback requestCallback = restTemplate.httpEntityCallback(Product,FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class,restTemplate.getMessageConverters());

        FakeStoreProductDto response= restTemplate.execute(url+id, HttpMethod.PATCH, requestCallback, responseExtractor);
        return convertFakeStoreProductToProduct(response);
    }
    public product replaceProduct(Long id,product Product){
        return null;
    }
    public void deleteProduct(Long id){

    }

    @Override
    public product addNewProduct(product Product) {
        return null;
    }


}
