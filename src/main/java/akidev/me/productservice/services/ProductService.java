package akidev.me.productservice.services;

import akidev.me.productservice.dtos.ProductDto;
import akidev.me.productservice.models.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getSingleProducts(Long productId);

    ProductDto addNewProduct(ProductDto product);

    void updateProduct(Long productId, ProductDto product);

    String deleteProduct(Long productId);
}
