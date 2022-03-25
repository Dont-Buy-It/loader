package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.BrandImageModel;
import com.dontbuyit.loader.model.BrandModel;
import com.dontbuyit.loader.model.ProductModel;
import com.dontbuyit.loader.model.dto.BrandsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

  private static final String BRANDS_CSV_STRING = "brandsCsvString";
  private static final String BRAND1_NAME = "brand1Name";
  private static final String BRAND2_NAME = "brand2Name";
  private static final String IMAGE_URL = "imageUrl";

  private final BrandModel brandModel1 = BrandModel.builder().name(BRAND1_NAME).build();
  private final BrandModel brandModel2 = BrandModel.builder().name(BRAND2_NAME).build();
  private final ProductModel productModel = ProductModel.builder().brandName(BRAND1_NAME).build();
  private final BrandImageModel brandImageModel = BrandImageModel.builder().brandName(BRAND1_NAME).imageUrl(IMAGE_URL).build();

  private final List<BrandModel> brandModels = List.of(brandModel2, brandModel1);
  private final List<ProductModel> productModels = List.of(productModel);
  private final List<BrandImageModel> brandImageModels = List.of(brandImageModel);

  @Mock
  private CsvLoadingService csvLoadingServiceMock;
  @Mock
  private CsvParsingService csvParsingServiceMock;
  @Mock
  private ProductService productServiceMock;
  @Mock
  private BrandImageService brandImageServiceMock;

  @InjectMocks
  private BrandService brandService;

  @Test
  void shouldSetBrandsSortedByName() {
    setUpMocks();

    final BrandsDto actualResult = brandService.getBrandsDto();

    assertEquals(List.of(brandModel1, brandModel2), actualResult.getBrands());
  }

  @Test
  void shouldSetLastUpdatedTime() {
    setUpMocks();

    final BrandsDto actualResult = brandService.getBrandsDto();

    assertNotNull(actualResult.getLastUpdatedTime());
  }

  @Test
  void shouldSetProductModels() {
    setUpMocks();

    brandService.getBrandsDto();

    assertEquals(productModels, brandModel1.getProducts());
  }

  @Test
  void shouldSetImageUrl() {
    setUpMocks();

    brandService.getBrandsDto();

    assertEquals(IMAGE_URL, brandModel1.getImageUrl());
  }

  private void setUpMocks() {
    when(csvLoadingServiceMock.loadBrandsCsv()).thenReturn(BRANDS_CSV_STRING);
    when(csvParsingServiceMock.parseCsv(BRANDS_CSV_STRING, BrandModel.class)).thenReturn(brandModels);
    when(productServiceMock.getProducts()).thenReturn(productModels);
    when(brandImageServiceMock.getBrandImages()).thenReturn(brandImageModels);
  }
}
