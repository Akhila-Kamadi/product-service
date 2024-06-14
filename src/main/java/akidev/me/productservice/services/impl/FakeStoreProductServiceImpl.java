package akidev.me.productservice.services.impl;

import akidev.me.productservice.clients.fakestoreapi.FakeStoreProductDto;
import akidev.me.productservice.dtos.ProductDto;
import akidev.me.productservice.models.Category;
import akidev.me.productservice.models.Product;
import akidev.me.productservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService {
    private final String baseUrl = "https://fakestoreapi.com/products";
    private RestTemplate restTemplate;

    public FakeStoreProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto convertProductToFakeStoreProductDto(Product product) {
        FakeStoreProductDto productDto= new FakeStoreProductDto();
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        productDto.setTitle(product.getTitle());
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }
    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(baseUrl, FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : response.getBody()) {
            Product product = convertFakeStoreProductDtoToProduct(productDto);

            products.add(product);
        }
        return products;
    }

    /*
    Returns a Product with all details for the specified productId.
    The ID of category will be null. But the name shall be correct.
     */
    @Override
    public Product getSingleProducts(Long productId) {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(baseUrl + "/{id}", FakeStoreProductDto.class, productId);
        FakeStoreProductDto productDto = response.getBody();
        Product product = convertFakeStoreProductDtoToProduct(productDto);

        return product;
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDto productDto = convertProductToFakeStoreProductDto(product);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(baseUrl, productDto, FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        Product fakeStoreProduct = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        return fakeStoreProduct;
    }

    //Product object has only those fields filled which need to be updated
    //Everything else is null
    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
