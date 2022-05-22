package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.BrandModel;
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
class BrandServiceTest {

    private static final String BRANDS_CSV_URL = "brandsCsvUrl";
    private static final String BRAND1_NAME = "brand1Name";
    private static final String BRAND2_NAME = "brand2Name";

    private final BrandModel brandModel1 = BrandModel.builder().name(BRAND1_NAME).build();
    private final BrandModel brandModel2 = BrandModel.builder().name(BRAND2_NAME).build();
    private final ProductModel productModel = ProductModel.builder().brandName(BRAND1_NAME).build();

    private final List<BrandModel> brandModels = List.of(brandModel2, brandModel1);
    private final List<ProductModel> productModels = List.of(productModel);

    @Mock
    private CsvParsingService csvParsingServiceMock;
    @Mock
    private ProductService productServiceMock;

    @InjectMocks
    private BrandService brandService;

    @Test
    void shouldSetBrandsSortedByName() {
        setUpMocks();

        final List<BrandModel> actualResult = brandService.getBrands();

        assertEquals(List.of(brandModel1, brandModel2), actualResult);
    }

    @Test
    void shouldSetProductModels() {
        setUpMocks();

        brandService.getBrands();

        assertEquals(productModels, brandModel1.getProducts());
    }

    private void setUpMocks() {
        setField(brandService, "brandsCsvUrl", BRANDS_CSV_URL);
        when(csvParsingServiceMock.parseCsv(BRANDS_CSV_URL, BrandModel.class)).thenReturn(brandModels);
        when(productServiceMock.getProducts()).thenReturn(productModels);
    }
}
