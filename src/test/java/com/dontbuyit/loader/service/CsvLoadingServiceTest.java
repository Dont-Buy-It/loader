package com.dontbuyit.loader.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings({"rawtypes", "unchecked"})
class CsvLoadingServiceTest {

  private static final String BRANDS_CSV_URL = "http://brandsCsvUrl.com";
  private static final String PRODUCTS_CSV_URL = "http://productsCsvUrl.com";
  private static final String ACTIONS_CSV_URL = "http://actionsCsvUrl.com";
  private static final String BRAND_IMAGES_CSV_URL = "http://brandImagesCsvUrl.com";

  private static final String BRANDS_CSV = "brandsCsv";
  private static final String PRODUCTS_CSV = "brandsCsv";
  private static final String ACTIONS_CSV = "brandsCsv";
  private static final String BRAND_IMAGES_CSV = "brandsCsv";

  @Mock
  private WebClient webClientMock;
  @Mock
  private RequestHeadersUriSpec requestHeadersUriSpecMock;
  @Mock
  private RequestHeadersSpec requestHeadersSpecMock;
  @Mock
  private WebClient.ResponseSpec responseSpecMock;

  @InjectMocks
  private CsvLoadingService csvLoadingService;

  @BeforeEach
  public void setUp() {
    setField(csvLoadingService, "brandsCsvUrl", BRANDS_CSV_URL);
    setField(csvLoadingService, "productsCsvUrl", PRODUCTS_CSV_URL);
    setField(csvLoadingService, "actionsCsvUrl", ACTIONS_CSV_URL);
    setField(csvLoadingService, "brandImagesCsvUrl", BRAND_IMAGES_CSV_URL);

    when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
    when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
  }

  @Test
  void shouldLoadBrandsCsv() {
    when(requestHeadersUriSpecMock.uri(eq(BRANDS_CSV_URL), any(Function.class))).thenReturn(requestHeadersSpecMock);
    when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(BRANDS_CSV));

    final String actualResult = csvLoadingService.loadBrandsCsv();

    assertEquals(BRANDS_CSV, actualResult);
  }

  @Test
  void shouldLoadProductsCsv() {
    when(requestHeadersUriSpecMock.uri(eq(PRODUCTS_CSV_URL), any(Function.class))).thenReturn(requestHeadersSpecMock);
    when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(PRODUCTS_CSV));

    final String actualResult = csvLoadingService.loadProductsCsv();

    assertEquals(PRODUCTS_CSV, actualResult);
  }

  @Test
  void shouldLoadActionsCsv() {
    when(requestHeadersUriSpecMock.uri(eq(ACTIONS_CSV_URL), any(Function.class))).thenReturn(requestHeadersSpecMock);
    when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(ACTIONS_CSV));

    final String actualResult = csvLoadingService.loadActionsCsv();

    assertEquals(ACTIONS_CSV, actualResult);
  }

  @Test
  void shouldLoadBrandImagesCsv() {
    when(requestHeadersUriSpecMock.uri(eq(BRAND_IMAGES_CSV_URL), any(Function.class))).thenReturn(requestHeadersSpecMock);
    when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(BRAND_IMAGES_CSV));

    final String actualResult = csvLoadingService.loadBrandImagesCsv();

    assertEquals(BRAND_IMAGES_CSV, actualResult);
  }
}
