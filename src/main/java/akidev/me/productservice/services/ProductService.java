package akidev.me.productservice.services;

import akidev.me.productservice.dtos.ProductDto;
import akidev.me.productservice.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getSingleProducts(Long productId);

    Product addNewProduct(Product product);

    Product updateProduct(Long productId, Product product);

    Product replaceProduct(Long productId, Product product);

    Product deleteProduct(Long productId);
}
