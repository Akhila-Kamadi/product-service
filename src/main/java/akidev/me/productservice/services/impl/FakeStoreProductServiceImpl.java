package akidev.me.productservice.services.impl;

import akidev.me.productservice.clients.fakestoreapi.FakeStoreClient;
import akidev.me.productservice.clients.fakestoreapi.FakeStoreProductDto;
import akidev.me.productservice.models.Category;
import akidev.me.productservice.models.Product;
import akidev.me.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService {
    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
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
        FakeStoreProductDto productDto = new FakeStoreProductDto();
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
        List<FakeStoreProductDto> productDtoList = fakeStoreClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : productDtoList) {
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
    public Optional<Product> getSingleProducts(Long productId) {
        FakeStoreProductDto productDto = fakeStoreClient.getSingleProducts(productId);
        if (productDto == null){
            return Optional.empty();
        }
        Product product = convertFakeStoreProductDtoToProduct(productDto);
        return Optional.of(product);
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDto productDto = convertProductToFakeStoreProductDto(product);
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.addNewProduct(productDto);
        Product fakeStoreProduct = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        return fakeStoreProduct;
    }

    //Product object has only those fields filled which need to be updated
    //Everything else is null
    @Override
    public Product updateProduct(Long productId, Product product) {

        FakeStoreProductDto productDto = new FakeStoreProductDto();
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        productDto.setTitle(product.getTitle());
        productDto.setCategory(product.getCategory().getName());

        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.updateProduct(productId, productDto);
        if (fakeStoreProductDto == null){
            return null;
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        FakeStoreProductDto productDto = convertProductToFakeStoreProductDto(product);
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.replaceProduct(productId, productDto);
        if (fakeStoreProductDto == null){
            return null;
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product deleteProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.deleteProduct(productId);
        if (fakeStoreProductDto == null){
            return null;
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }
}
