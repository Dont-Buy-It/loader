package com.dontbuyit.loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.JsonExpectationsHelper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.function.Function;

import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.resourceToString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings({"rawtypes", "unchecked"})
public class BrandsTest {

  private static final String URL = "/api/1.0/brands";
  private static final String BRANDS_CSV_URL = "http://brands.csv.url.com";
  private static final String BRAND_IMAGES_CSV_URL = "http://brand-images.csv.url.com";
  private static final String PRODUCTS_CSV_URL = "http://products.csv.url.com";

  @MockBean
  private WebClient webClientMock;

  @Mock
  private RequestHeadersUriSpec requestHeadersUriSpecMock;

  @Mock
  private RequestHeadersSpec brandsRequestHeadersSpecMock;
  @Mock
  private RequestHeadersSpec brandImagesRequestHeadersSpecMock;
  @Mock
  private RequestHeadersSpec productsRequestHeadersSpecMock;

  @Mock
  private ResponseSpec brandsResponseSpecMock;
  @Mock
  private ResponseSpec brandImagesResponseSpecMock;
  @Mock
  private ResponseSpec productsResponseSpecMock;

  @Autowired
  protected MockMvc mockMvc;

  @Test
  void shouldReturnBrands() throws Exception {
    when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);

    when(requestHeadersUriSpecMock.uri(eq(BRANDS_CSV_URL), any(Function.class))).thenReturn(brandsRequestHeadersSpecMock);
    when(brandsRequestHeadersSpecMock.retrieve()).thenReturn(brandsResponseSpecMock);
    when(brandsResponseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(getBrandsCsv()));

    when(requestHeadersUriSpecMock.uri(eq(BRAND_IMAGES_CSV_URL), any(Function.class))).thenReturn(brandImagesRequestHeadersSpecMock);
    when(brandImagesRequestHeadersSpecMock.retrieve()).thenReturn(brandImagesResponseSpecMock);
    when(brandImagesResponseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(getBrandImagesCsv()));

    when(requestHeadersUriSpecMock.uri(eq(PRODUCTS_CSV_URL), any(Function.class))).thenReturn(productsRequestHeadersSpecMock);
    when(productsRequestHeadersSpecMock.retrieve()).thenReturn(productsResponseSpecMock);
    when(productsResponseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(getProductsCsv()));

    final String responseString = mockMvc
        .perform(get(URL))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();
    final String brandsResponse = new ObjectMapper()
        .readValue(responseString, JsonNode.class)
        .findValue("brands")
        .toString();

    new JsonExpectationsHelper().assertJsonEqual(getBrandsResponse(), brandsResponse);
  }

  private String getBrandsCsv() throws IOException {
    return resourceToString("/csv/brands.csv", defaultCharset());
  }

  private String getBrandImagesCsv() throws IOException {
    return resourceToString("/csv/brandImages.csv", defaultCharset());
  }

  private String getProductsCsv() throws IOException {
    return resourceToString("/csv/products.csv", defaultCharset());
  }

  private String getBrandsResponse() throws IOException {
    final String brandsResponse = resourceToString("/response/brands.json", defaultCharset());
    return new ObjectMapper().readValue(brandsResponse, JsonNode.class).toString();
  }
}
