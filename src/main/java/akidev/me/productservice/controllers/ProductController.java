package akidev.me.productservice.controllers;

import akidev.me.productservice.dtos.ProductDto;
import akidev.me.productservice.services.ProductService;
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
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductDto getSingleProducts(@PathVariable("productId") Long productId){
        return productService.getSingleProducts(productId);
    }


    @PostMapping()
    public ProductDto addNewProduct(@RequestBody ProductDto productDto){
        return productService.addNewProduct(productDto);
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto newProductDetails){
        productService.updateProduct(productId,newProductDetails);
        return "updated";
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
        return "Deleting a Product with id: "+productId;
    }
}
