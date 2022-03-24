package com.dontbuyit.loader;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.function.Function;

import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.resourceToString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings({"rawtypes", "unchecked"})
public class ProductsTest {

  private static final String URL = "/api/1.0/products";
  private static final String PRODUCTS_CSV_URL = "http://products.csv.url.com";

  @MockBean
  private WebClient webClientMock;

  @Mock
  private RequestHeadersUriSpec requestHeadersUriSpecMock;
  @Mock
  private RequestHeadersSpec requestHeadersSpecMock;
  @Mock
  private WebClient.ResponseSpec responseSpecMock;

  @Autowired
  protected MockMvc mockMvc;

  @Test
  void shouldReturnProducts() throws Exception {
    when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
    when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
    when(requestHeadersUriSpecMock.uri(eq(PRODUCTS_CSV_URL), any(Function.class))).thenReturn(requestHeadersSpecMock);
    when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(getProductsCsv()));

    mockMvc
        .perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(content().json(getProductsResponse()));
  }

  private String getProductsCsv() throws IOException {
    return resourceToString("/csv/products.csv", defaultCharset());
  }

  private String getProductsResponse() throws IOException {
    return resourceToString("/response/products.json", defaultCharset());
  }
}
