package akidev.me.productservice.services.impl;

import akidev.me.productservice.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService {

    private RestTemplate restTemplate;
    @Override
    public String getAllCategories() {
        return null;
    }

    @Override
    public String getProductsInCategory(Long categoryId) {
        return null;
    }
}
