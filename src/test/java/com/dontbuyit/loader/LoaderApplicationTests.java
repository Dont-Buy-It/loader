package com.dontbuyit.loader;

import com.dontbuyit.loader.service.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class LoaderApplicationTests {

    @MockBean
    private CacheService cacheServiceMock;

    @Test
    void contextLoads() {
    }
}
