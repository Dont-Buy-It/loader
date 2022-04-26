package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.BrandModel;
import com.dontbuyit.loader.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BrandSearchService {

    private static final int DEFAULT_OFFSET = 0;

    private final BrandService brandService;

    public List<BrandModel> searchBrands(Long page, Long limit, String search) {
        final List<BrandModel> filteredBrands = filterBrands(brandService.getBrands(), search);
        return paginateBrands(filteredBrands, page, limit);
    }

    private List<BrandModel> filterBrands(List<BrandModel> brandModels, String search) {
        return brandModels.stream()
                .filter(nonNull(search) ? brandModel -> brandMatchesSearch(brandModel, search.trim()) : brandModel -> true)
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
