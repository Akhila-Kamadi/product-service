package akidev.me.productservice.controllers;

import akidev.me.productservice.dtos.GetSingleProductResponseDto;
import akidev.me.productservice.dtos.ProductDto;
import akidev.me.productservice.models.Category;
import akidev.me.productservice.models.Product;
import akidev.me.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private ProductDto convertProductToProductDto(Product product) {
        ProductDto productDto= new ProductDto();
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        productDto.setTitle(product.getTitle());
        productDto.setCategory(product.getCategory().getName());
        return productDto;
    }

    private static Product convertProductDtoToProduct(ProductDto productDto) {
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

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product: products){
            productDtoList.add(convertProductToProductDto(product));
        }

        ResponseEntity<List<ProductDto>> response = new ResponseEntity<>(productDtoList,
                HttpStatus.OK);
        return response;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProducts(@PathVariable("productId") Long productId){
        Product product = productService.getSingleProducts(productId);
        ProductDto productDto = convertProductToProductDto(product);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token", "noaccess4uheyhey");

        ResponseEntity<ProductDto> response = new ResponseEntity<>(
                productDto,
                headers,
                HttpStatus.OK
        );

        return response;
    }


    @PostMapping()
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto productDto){
        Product product = convertProductDtoToProduct(productDto);
        Product productResponse = productService.addNewProduct(product);
        ProductDto productDto1 = convertProductToProductDto(productResponse);
        ResponseEntity<ProductDto> response = new ResponseEntity<>(productDto1, HttpStatus.CREATED);
        return response;
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto newProductDetails){
        Product product = convertProductDtoToProduct(newProductDetails);
        Product productResponse = productService.updateProduct(productId, product);
        ProductDto productDto1 = convertProductToProductDto(productResponse);
        ResponseEntity<ProductDto> response = new ResponseEntity<>(productDto1, HttpStatus.OK);
        return response;
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto newProductDetails){
        Product product = convertProductDtoToProduct(newProductDetails);
        Product productResponse = productService.replaceProduct(productId, product);
        ProductDto productDto1 = convertProductToProductDto(productResponse);
        ResponseEntity<ProductDto> response = new ResponseEntity<>(productDto1, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("productId") Long productId){
        Product product = productService.deleteProduct(productId);
        ProductDto productDto = convertProductToProductDto(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
