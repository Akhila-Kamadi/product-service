package akidev.me.productservice.services.impl;

import akidev.me.productservice.clients.fakestoreapi.FakeStoreClient;
import akidev.me.productservice.clients.fakestoreapi.FakeStoreProductDto;
import akidev.me.productservice.models.Category;
import akidev.me.productservice.models.Product;
import akidev.me.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService {
    private FakeStoreClient fakeStoreClient;

    public FakeStoreCategoryServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public List<String> getAllCategories() {
        return fakeStoreClient.getAllCategories();
    }

    @Override
    public List<Product> getProductsInCategory(String category) {
        List<FakeStoreProductDto> productsInCategoryDtoList = fakeStoreClient.getProductsInCategory(category);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto: productsInCategoryDtoList){
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setImageUrl(productDto.getImage());
            Category category1 = new Category();
            category1.setName(productDto.getCategory());
            product.setCategory(category1);
            products.add(product);
        }
        return products;
    }
}
