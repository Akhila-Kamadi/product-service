package akidev.me.productservice.services.impl;

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


    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(baseUrl, ProductDto[].class);
        List<Product> products = new ArrayList<>();
        for (ProductDto productDto: response.getBody()){
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setImageUrl(productDto.getImage());
            Category category = new Category();
            category.setName(productDto.getCategory());
            product.setCategory(category);

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
        ResponseEntity<ProductDto> response = restTemplate.getForEntity(baseUrl + "/{id}", ProductDto.class, productId);

        ProductDto productDto = response.getBody();

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

    @Override
    public Product addNewProduct(ProductDto productDto) {
        ResponseEntity<ProductDto> response = restTemplate.postForEntity(baseUrl, productDto, ProductDto.class);

        ProductDto productDto1 = response.getBody();

        Product product = new Product();
        product.setId(productDto1.getId());
        product.setTitle(productDto1.getTitle());
        product.setPrice(productDto1.getPrice());
        product.setDescription(productDto1.getDescription());
        product.setImageUrl(productDto1.getImage());
        Category category = new Category();
        category.setName(productDto1.getCategory());
        product.setCategory(category);

        return product;
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
