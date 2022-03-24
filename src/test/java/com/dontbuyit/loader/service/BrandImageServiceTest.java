package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.BrandImageModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandImageServiceTest {

  private static final String BRAND_IMAGES_CSV_STRING = "brandImagesCsvString";

  private final List<BrandImageModel> brandImageModels = List.of(BrandImageModel.builder().build());

  @Mock
  private CsvLoadingService csvLoadingServiceMock;
  @Mock
  private CsvParsingService csvParsingServiceMock;

  @InjectMocks
  private BrandImageService brandImageService;

  @Test
  void shouldReturnActions() {
    when(csvLoadingServiceMock.loadBrandImagesCsv()).thenReturn(BRAND_IMAGES_CSV_STRING);
    when(csvParsingServiceMock.parseCsv(BRAND_IMAGES_CSV_STRING, BrandImageModel.class)).thenReturn(brandImageModels);

    final List<BrandImageModel> actualResult = brandImageService.getBrandImages();

    assertEquals(brandImageModels, actualResult);
  }
}
