package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.BrandImageModel;
import com.dontbuyit.loader.model.BrandModel;
import com.dontbuyit.loader.model.ProductModel;
import com.dontbuyit.loader.model.dto.BrandsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class BrandService {

  @Autowired
  private CsvLoadingService csvLoadingService;
  @Autowired
  private CsvParsingService csvParsingService;
  @Autowired
  private ProductService productService;
  @Autowired
  private BrandImageService brandImageService;

  @Cacheable(value = "${cache.brands.name}")
  public BrandsDto getBrandsDto() {
    final String brandsCsv = csvLoadingService.loadBrandsCsv();
    final List<BrandModel> brandModels = csvParsingService.parseCsv(brandsCsv, BrandModel.class);
    final Map<String, List<ProductModel>> productsByBrandNames = productService.getProducts().stream()
        .collect(groupingBy(productModel -> productModel.getBrandName().toLowerCase()));
    brandModels.forEach(brandModel -> attachProducts(brandModel, productsByBrandNames));
    final List<BrandImageModel> brandImageModels = brandImageService.getBrandImages();
    brandModels.forEach(brandModel -> attachImage(brandModel, brandImageModels));
    final List<BrandModel> sortedBrandModels = brandModels.stream()
        .sorted(comparing(BrandModel::getName))
        .collect(toList());
    return BrandsDto.builder()
        .brands(sortedBrandModels)
        .lastUpdatedTime(ZonedDateTime.now())
        .build();
  }

  @Scheduled(cron = "${cache.brands.cron}")
  @CacheEvict(value = "${cache.brands.name}", allEntries = true)
  public void clearCache() {
  }

  private void attachProducts(BrandModel brandModel, Map<String, List<ProductModel>> productsByBrandNames) {
    final List<ProductModel> filteredProducts = productsByBrandNames
        .getOrDefault(brandModel.getName().toLowerCase(), List.of());
    brandModel.setProducts(filteredProducts);
  }

  private void attachImage(BrandModel brandModel, List<BrandImageModel> brandImageModels) {
    brandImageModels.stream()
        .filter(brandImageModel ->
            Objects.equals(brandImageModel.getBrandName().toLowerCase(), brandModel.getName().toLowerCase()))
        .findFirst()
        .map(BrandImageModel::getImageUrl)
        .ifPresent(brandModel::setImageUrl);
  }
}
