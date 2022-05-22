package com.dontbuyit.loader;

import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.resourceToString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dontbuyit.loader.service.CacheService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings({"rawtypes", "unchecked"})
public class BrandsTest {

    private static final String URL = "/api/brands";
    private static final String BRANDS_CSV_URL = "http://brands.csv.url.com";
    private static final String PRODUCTS_CSV_URL = "http://products.csv.url.com";

    @MockBean
    private WebClient webClientMock;
    @MockBean
    private CacheService cacheServiceMock;

    @Mock
    private RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private RequestHeadersSpec brandsRequestHeadersSpecMock;
    @Mock
    private RequestHeadersSpec productsRequestHeadersSpecMock;

    @Mock
    private ResponseSpec brandsResponseSpecMock;
    @Mock
    private ResponseSpec productsResponseSpecMock;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void shouldReturnBrands() throws Exception {
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);

        when(requestHeadersUriSpecMock.uri(BRANDS_CSV_URL)).thenReturn(brandsRequestHeadersSpecMock);
        when(brandsRequestHeadersSpecMock.retrieve()).thenReturn(brandsResponseSpecMock);
        when(brandsResponseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(getBrandsCsv()));

        when(requestHeadersUriSpecMock.uri(PRODUCTS_CSV_URL)).thenReturn(productsRequestHeadersSpecMock);
        when(productsRequestHeadersSpecMock.retrieve()).thenReturn(productsResponseSpecMock);
        when(productsResponseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(getProductsCsv()));

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().json(getBrandsResponse()));
    }

    private String getBrandsCsv() throws IOException {
        return resourceToString("/csv/brands.csv", defaultCharset());
    }

    private String getProductsCsv() throws IOException {
        return resourceToString("/csv/products.csv", defaultCharset());
    }

    private String getBrandsResponse() throws IOException {
        final String brandsResponse = resourceToString("/response/brands.json", defaultCharset());
        return new ObjectMapper().readValue(brandsResponse, JsonNode.class).toString();
    }
}
