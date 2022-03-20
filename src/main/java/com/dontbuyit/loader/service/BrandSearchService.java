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

  @Autowired
  private BrandService brandService;

  public BrandsDto searchBrands(Long page, Long limit, String search) {
    final BrandsDto brandsDto = brandService.getBrandsDto();
    final List<BrandModel> filteredBrands = filterBrands(brandsDto.getBrands(), search);
    final List<BrandModel> brands = paginateBrands(filteredBrands, page, limit);
    return BrandsDto.of(brands, brandsDto.getLastUpdatedTime());
  }

  private List<BrandModel> filterBrands(List<BrandModel> brandModels, String search) {
    return brandModels.stream()
        .filter(nonNull(search) ? brandModel -> brandMatchesSearch(brandModel, search) : brandModel -> true)
        .collect(toList());
  }

  private List<BrandModel> paginateBrands(List<BrandModel> brandModels, Long page, Long limit) {
    return brandModels.stream()
        .sorted(comparing(BrandModel::getName))
        .skip((nonNull(page) && nonNull(limit)) ? page * limit : 0)
        .limit(nonNull(limit) ? limit : brandModels.size())
        .collect(toList());
  }

  private boolean brandMatchesSearch(BrandModel brandModel, String search) {
    final String lowerCaseSearch = search.toLowerCase();
    return brandModel.getName().toLowerCase().contains(lowerCaseSearch) ||
        brandModel.getProductModels().stream()
            .map(ProductModel::getName)
            .map(String::toLowerCase)
            .anyMatch(name -> name.contains(lowerCaseSearch));
  }
}
