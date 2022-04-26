package com.dontbuyit.loader.controller;

import com.dontbuyit.loader.model.BrandModel;
import com.dontbuyit.loader.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brands")
public class BrandsController {

    private final BrandService brandService;

    @GetMapping
    public List<BrandModel> getBrands() {
        return brandService.getBrands();
    }
}
