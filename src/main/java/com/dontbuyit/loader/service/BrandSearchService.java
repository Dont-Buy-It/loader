package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.BrandModel;
import com.dontbuyit.loader.model.ProductModel;
import com.dontbuyit.loader.model.dto.BrandsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Service
public class BrandSearchService {

  private static final int DEFAULT_OFFSET = 0;

  @Autowired
  private BrandService brandService;

  public BrandsDto searchBrands(Long page, Long limit, String search) {
    final BrandsDto brandsDto = brandService.getBrandsDto();
    final List<BrandModel> filteredBrands = filterBrands(brandsDto.getBrands(), search);
    final List<BrandModel> brands = paginateBrands(filteredBrands, page, limit);
    return BrandsDto.builder()
        .brands(brands)
        .lastUpdatedTime(brandsDto.getLastUpdatedTime())
        .build();
  }

  private List<BrandModel> filterBrands(List<BrandModel> brandModels, String search) {
    return brandModels.stream()
        .filter(nonNull(search) ? brandModel -> brandMatchesSearch(brandModel, search) : brandModel -> true)
        .collect(toList());
  }

  private List<BrandModel> paginateBrands(List<BrandModel> brandModels, Long page, Long limit) {
    return brandModels.stream()
        .skip((nonNull(page) && nonNull(limit)) ? page * limit : DEFAULT_OFFSET)
        .limit(nonNull(limit) ? limit : brandModels.size())
        .collect(toList());
  }

  private boolean brandMatchesSearch(BrandModel brandModel, String search) {
    final String lowerCaseSearch = search.toLowerCase();
    return brandModel.getName().toLowerCase().contains(lowerCaseSearch) ||
        brandModel.getProducts().stream()
            .map(ProductModel::getName)
            .map(String::toLowerCase)
            .anyMatch(name -> name.contains(lowerCaseSearch));
  }
}
