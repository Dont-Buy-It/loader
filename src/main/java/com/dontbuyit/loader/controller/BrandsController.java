package com.dontbuyit.loader.controller;

import com.dontbuyit.loader.model.dto.BrandsDto;
import com.dontbuyit.loader.service.BrandSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/${api.version}/brands")
public class BrandsController {

  @Autowired
  private BrandSearchService brandSearchService;

  @GetMapping
  public BrandsDto getBrands(
      @RequestParam(required = false) Long page,
      @RequestParam(required = false) Long limit,
      @RequestParam(required = false) String search) {
    return brandSearchService.searchBrands(page, limit, search);
  }
}
