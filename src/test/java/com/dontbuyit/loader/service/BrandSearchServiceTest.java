package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.BrandModel;
import com.dontbuyit.loader.model.ProductModel;
import com.dontbuyit.loader.model.dto.BrandsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandSearchServiceTest {

  private static final String SEARCH_STRING = "searchString";
  private static final String BRAND_NAME_WITH_SEARCH_STRING = "brandName" + SEARCH_STRING;
  private static final String BRAND_NAME_WITHOUT_SEARCH_STRING = "brandName";
  private static final String PRODUCT_NAME_WITHOUT_SEARCH_STRING = "productName";
  private static final String PRODUCT_NAME_WITH_SEARCH_STRING = "productName" + SEARCH_STRING;

  private final ProductModel productModelWithoutSearchedName = ProductModel.builder()
      .name(PRODUCT_NAME_WITHOUT_SEARCH_STRING)
      .build();
  private final ProductModel productModelWithSearchedName = ProductModel.builder()
      .name(PRODUCT_NAME_WITH_SEARCH_STRING)
      .build();
  private final BrandModel brandModelWithoutSearchedName = BrandModel.builder()
      .name(BRAND_NAME_WITHOUT_SEARCH_STRING)
      .products(List.of(productModelWithoutSearchedName))
      .build();
  private final BrandModel brandModelWithSearchedName = BrandModel.builder()
      .name(BRAND_NAME_WITH_SEARCH_STRING)
      .products(List.of(productModelWithoutSearchedName))
      .build();
  private final BrandModel brandModelWithSearchedProductName = BrandModel.builder()
      .name(BRAND_NAME_WITHOUT_SEARCH_STRING)
      .products(List.of(productModelWithSearchedName))
      .build();
  private final List<BrandModel> brandModels = List.of(
      brandModelWithoutSearchedName, brandModelWithSearchedName, brandModelWithSearchedProductName);
  private final BrandsDto brandsDto = BrandsDto.builder().brands(brandModels).build();

  @Mock
  private BrandService brandServiceMock;

  @InjectMocks
  private BrandSearchService brandSearchService;

  @BeforeEach
  void setUp() {
    when(brandServiceMock.getBrandsDto()).thenReturn(brandsDto);
  }

  @Test
  void shouldReturnBrandsWithoutFiltersAndSearch() {
    final BrandsDto actualResult = brandSearchService.searchBrands(null, null, null);

    assertEquals(brandModels, actualResult.getBrands());
  }

  @Test
  void shouldReturnBrandsBySearch() {
    final BrandsDto actualResult = brandSearchService.searchBrands(null, null, SEARCH_STRING);

    assertEquals(List.of(brandModelWithSearchedName, brandModelWithSearchedProductName), actualResult.getBrands());
  }

  @Test
  void shouldReturnBrandsLimited() {
    final long limit = 2;

    final BrandsDto actualResult = brandSearchService.searchBrands(null, limit, null);

    assertEquals(List.of(brandModelWithoutSearchedName, brandModelWithSearchedName), actualResult.getBrands());
  }

  @Test
  void shouldReturnBrandsPaginated() {
    final long page = 1;
    final long limit = 2;

    final BrandsDto actualResult = brandSearchService.searchBrands(page, limit, null);

    assertEquals(List.of(brandModelWithSearchedProductName), actualResult.getBrands());
  }

  @Test
  void shouldReturnBrandsBySearchPaginated() {
    final long page = 0;
    final long limit = 1;

    final BrandsDto actualResult = brandSearchService.searchBrands(page, limit, SEARCH_STRING);

    assertEquals(List.of(brandModelWithSearchedName), actualResult.getBrands());
  }
}
