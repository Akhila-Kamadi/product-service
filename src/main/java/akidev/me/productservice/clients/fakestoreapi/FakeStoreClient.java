package akidev.me.productservice.clients.fakestoreapi;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreClient {

    private RestTemplate restTemplate;

    public FakeStoreClient(@Qualifier("restTemplateWithHttpClient") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String baseUrl = "https://fakestoreapi.com/products";

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url,
                                                  @Nullable Object request,
                                                  Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public List<FakeStoreProductDto> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(baseUrl, FakeStoreProductDto[].class);
        return Arrays.asList(response.getBody());
    }

    public FakeStoreProductDto getSingleProducts(Long productId) {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(baseUrl + "/{id}", FakeStoreProductDto.class, productId);
        return response.getBody();
    }

    public FakeStoreProductDto addNewProduct(FakeStoreProductDto productDto) {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(baseUrl, productDto, FakeStoreProductDto.class);
        return response.getBody();
    }

    public FakeStoreProductDto updateProduct(Long productId, FakeStoreProductDto productDto) {
        ResponseEntity<FakeStoreProductDto> response = requestForEntity(
                HttpMethod.PATCH,
                baseUrl + "/{id}",
                productDto,
                FakeStoreProductDto.class,
                productId
        );
        return response.getBody();
    }

    public FakeStoreProductDto replaceProduct(Long productId, FakeStoreProductDto productDto) {
        ResponseEntity<FakeStoreProductDto> response = requestForEntity(
                HttpMethod.PUT,
                baseUrl + "/{id}",
                productDto,
                FakeStoreProductDto.class,
                productId
        );
        return response.getBody();
    }

    public FakeStoreProductDto deleteProduct(Long productId) {
        ResponseEntity<FakeStoreProductDto> response = requestForEntity(
                HttpMethod.DELETE,
                baseUrl + "/{id}",
                null,
                FakeStoreProductDto.class,
                productId
        );
        return response.getBody();
    }

    public List<String> getAllCategories() {
        ResponseEntity<String[]> response =
                restTemplate.getForEntity(baseUrl + "/categories", String[].class);
        return List.of(response.getBody());
    }

    public List<FakeStoreProductDto> getProductsInCategory(String category) {
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(baseUrl + "/category/{category}",
                FakeStoreProductDto[].class,
                category);
        return Arrays.asList(response.getBody());
    }
}
