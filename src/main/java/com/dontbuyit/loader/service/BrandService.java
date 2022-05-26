package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.BrandModel;
import com.dontbuyit.loader.model.ProductModel;
import com.dontbuyit.loader.model.UkrainianBrandModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final CsvParsingService csvParsingService;
    private final ProductService productService;

    @Value("${services.external.csv.brands.url}")
    private String brandsCsvUrl;
    @Value("${services.external.csv.ukrainianBrands.url}")
    private String ukrainianBrandsCsvUrl;

    @Cacheable(value = "brands")
    public List<BrandModel> getBrands() {
        final List<BrandModel> brandModels = csvParsingService
                .parseCsv(brandsCsvUrl, BrandModel.class);
        final List<BrandModel> ukrainianBrandModels = csvParsingService
                .parseCsv(ukrainianBrandsCsvUrl, UkrainianBrandModel.class).stream()
                .map(UkrainianBrandModel::toBrandModel)
                .toList();
        final List<BrandModel> allBrandModels = ListUtils.union(brandModels, ukrainianBrandModels);
        final Map<String, List<ProductModel>> productsByBrandNames = productService.getProducts().stream()
                .collect(groupingBy(productModel -> productModel.getBrandName().toLowerCase()));
        return allBrandModels.stream()
                .peek(brandModel -> attachProducts(brandModel, productsByBrandNames))
                .sorted(comparing(BrandModel::getName))
                .collect(toList());
    }

    private void attachProducts(BrandModel brandModel, Map<String, List<ProductModel>> productsByBrandNames) {
        final List<ProductModel> filteredProducts = productsByBrandNames
                .getOrDefault(brandModel.getName().toLowerCase(), List.of());
        brandModel.setProducts(filteredProducts);
    }
}
