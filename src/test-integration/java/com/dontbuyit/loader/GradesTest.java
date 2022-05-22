package com.dontbuyit.loader;

import com.dontbuyit.loader.service.CacheService;
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

import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.resourceToString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings({"rawtypes", "unchecked"})
public class GradesTest {

    private static final String URL = "/api/grades";
    private static final String GRADES_CSV_URL = "http://grades.csv.url.com";

    @MockBean
    private WebClient webClientMock;
    @MockBean
    private CacheService cacheServiceMock;

    @Mock
    private RequestHeadersUriSpec requestHeadersUriSpecMock;
    @Mock
    private RequestHeadersSpec requestHeadersSpecMock;
    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void shouldReturnGrades() throws Exception {
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(GRADES_CSV_URL)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(String.class)).thenReturn(Mono.just(getGradesCsv()));

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().json(getGradesResponse()));
    }

    private String getGradesCsv() throws IOException {
        return resourceToString("/csv/grades.csv", defaultCharset());
    }

    private String getGradesResponse() throws IOException {
        return resourceToString("/response/grades.json", defaultCharset());
    }
}
