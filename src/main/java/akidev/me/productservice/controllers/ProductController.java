package akidev.me.productservice.controllers;

import akidev.me.productservice.dtos.GetSingleProductResponseDto;
import akidev.me.productservice.dtos.ProductDto;
import akidev.me.productservice.models.Product;
import akidev.me.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetSingleProductResponseDto> getSingleProducts(@PathVariable("productId") Long productId){
        Product product = productService.getSingleProducts(productId);
        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
        responseDto.setProduct(product);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token", "noaccess4uheyhey");

        ResponseEntity<GetSingleProductResponseDto> response = new ResponseEntity<>(
                responseDto,
                headers,
                HttpStatus.OK
        );

        return response;
    }


    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto){
        Product product = productService.addNewProduct(productDto);
        ResponseEntity<Product> response = new ResponseEntity<>(product, HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto newProductDetails){
        return "updated";
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "Deleting a Product with id: "+productId;
    }
}
