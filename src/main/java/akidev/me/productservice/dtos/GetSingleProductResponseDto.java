package akidev.me.productservice.dtos;

import akidev.me.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetSingleProductResponseDto {
    private Product product;
}
