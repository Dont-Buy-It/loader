package com.dontbuyit.loader.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CacheServiceTest {

    @Mock
    private BrandService brandServiceMock;
    @Mock
    private GradeService gradeServiceMock;

    @InjectMocks
    private CacheService cacheService;

    @Test
    void shouldNotThrowExceptionsOnClearCache() {
        cacheService.refreshCache();
    }
}
