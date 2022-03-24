package com.dontbuyit.loader.controller;

import com.dontbuyit.loader.model.ProductModel;
import com.dontbuyit.loader.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {

  private final List<ProductModel> productModels = List.of();

  @Mock
  private ProductService productServiceMock;

  @InjectMocks
  private ProductsController productsController;

  @Test
  void shouldReturnActions() {
    when(productServiceMock.getProducts()).thenReturn(productModels);

    final List<ProductModel> actualResult = productsController.getProducts();

    assertEquals(productModels, actualResult);
  }
}
