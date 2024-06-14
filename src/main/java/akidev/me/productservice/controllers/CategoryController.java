package akidev.me.productservice.controllers;

import akidev.me.productservice.dtos.ProductDto;
import akidev.me.productservice.exceptions.NotFoundException;
import akidev.me.productservice.models.Product;
import akidev.me.productservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private ProductDto convertProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        productDto.setTitle(product.getTitle());
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDto>> getProductsInCategory(@PathVariable("category") String category) throws NotFoundException {
        List<Product> productsInCategory = categoryService.getProductsInCategory(category);
        if (productsInCategory==null){
            throw new NotFoundException("No Product with category: "+category);
        }
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productsInCategory) {
            ProductDto productDto = convertProductToProductDto(product);
            productDtoList.add(productDto);
        }
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }
}
