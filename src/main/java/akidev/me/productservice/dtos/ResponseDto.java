package akidev.me.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {
    private ResponseStatus responseStatus;
    private String message;
}
