package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  private static final String PRODUCTS_CSV_STRING = "productsCsvString";

  private final ProductModel productModel1 = ProductModel.builder().name("a").build();
  private final ProductModel productModel2 = ProductModel.builder().name("b").build();

  @Mock
  private CsvLoadingService csvLoadingServiceMock;
  @Mock
  private CsvParsingService csvParsingServiceMock;

  @InjectMocks
  private ProductService productService;

  @Test
  void shouldReturnProductsSortedByName() {
    final List<ProductModel> unsortedProducts = List.of(productModel2, productModel1);
    final List<ProductModel> sortedProducts = List.of(productModel1, productModel2);
    when(csvLoadingServiceMock.loadProductsCsv()).thenReturn(PRODUCTS_CSV_STRING);
    when(csvParsingServiceMock.parseCsv(PRODUCTS_CSV_STRING, ProductModel.class)).thenReturn(unsortedProducts);

    final List<ProductModel> actualResult = productService.getProducts();

    assertEquals(sortedProducts, actualResult);
  }
}
