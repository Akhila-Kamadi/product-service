package akidev.me.productservice.services;

import akidev.me.productservice.models.Category;
import akidev.me.productservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryService {

    List<String> getAllCategories();

    List<Product> getProductsInCategory(String category);
}
