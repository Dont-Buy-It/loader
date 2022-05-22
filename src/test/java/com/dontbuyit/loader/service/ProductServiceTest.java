package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    private static final String PRODUCTS_CSV_URL = "productsCsvUrl";

    private final ProductModel productModel1 = ProductModel.builder().name("a").brandName("a").build();
    private final ProductModel productModel2 = ProductModel.builder().name("b").brandName("b").build();

    @Mock
    private CsvParsingService csvParsingServiceMock;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnProductsSortedByName() {
        setField(productService, "productsCsvUrl", PRODUCTS_CSV_URL);
        final List<ProductModel> unsortedProducts = List.of(productModel2, productModel1);
        final List<ProductModel> sortedProducts = List.of(productModel1, productModel2);
        when(csvParsingServiceMock.parseCsv(PRODUCTS_CSV_URL, ProductModel.class)).thenReturn(unsortedProducts);

        final List<ProductModel> actualResult = productService.getProducts();

        assertEquals(sortedProducts, actualResult);
    }
}
