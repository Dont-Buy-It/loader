package com.dontbuyit.loader.controller;

import com.dontbuyit.loader.model.dto.BrandsDto;
import com.dontbuyit.loader.service.BrandSearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandsControllerTest {

  private final BrandsDto brandsDto = BrandsDto.builder().build();

  @Mock
  private BrandSearchService brandSearchServiceMock;

  @InjectMocks
  private BrandsController brandsController;

  @Test
  void shouldReturnActions() {
    final long page = 1;
    final long limit = 1;
    final String search = "search";
    when(brandSearchServiceMock.searchBrands(page, limit, search)).thenReturn(brandsDto);

    final BrandsDto actualResult = brandsController.getBrands(page, limit, search);

    assertEquals(brandsDto, actualResult);
  }
}
