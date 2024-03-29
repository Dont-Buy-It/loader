package com.dontbuyit.loader.controller;

import com.dontbuyit.loader.model.BrandModel;
import com.dontbuyit.loader.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BrandsControllerTest {

    private final BrandModel brandModel = BrandModel.builder().build();

    @Mock
    private BrandService brandServiceMock;

    @InjectMocks
    private BrandsController brandsController;

    @Test
    void shouldReturnActions() {
        when(brandServiceMock.getBrands()).thenReturn(List.of(brandModel));

        final List<BrandModel> actualResult = brandsController.getBrands();

        assertEquals(List.of(brandModel), actualResult);
    }
}
