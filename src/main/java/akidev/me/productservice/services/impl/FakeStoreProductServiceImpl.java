package akidev.me.productservice.services.impl;

import akidev.me.productservice.dtos.ProductDto;
import akidev.me.productservice.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService {
    private final String baseUrl = "https://fakestoreapi.com/products";
    private RestTemplate restTemplate;

    public FakeStoreProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = restTemplate.getForObject(baseUrl, List.class);
        return products;
    }

    @Override
    public ProductDto getSingleProducts(Long productId) {
        return restTemplate.getForObject(baseUrl + "/" + productId, ProductDto.class);
    }

    @Override
    public ProductDto addNewProduct(ProductDto product) {
        return restTemplate.postForObject(baseUrl,product,ProductDto.class);
    }

    @Override
    public void updateProduct(Long productId, ProductDto product) {
        restTemplate.put(baseUrl+"/"+productId,product,Void.class);
    }

    @Override
    public String deleteProduct(Long productId) {
        restTemplate.delete(baseUrl+"/"+productId);
        return "deleted";
    }
}
