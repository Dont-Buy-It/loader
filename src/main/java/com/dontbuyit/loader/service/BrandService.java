package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.BrandModel;
import com.dontbuyit.loader.model.ProductModel;
import lombok.RequiredArgsConstructor;
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

    @Cacheable(value = "brands")
    public List<BrandModel> getBrands() {
        final List<BrandModel> brandModels = csvParsingService.parseCsv(brandsCsvUrl, BrandModel.class);
        final Map<String, List<ProductModel>> productsByBrandNames = productService.getProducts().stream()
                .collect(groupingBy(productModel -> productModel.getBrandName().toLowerCase()));
        return brandModels.stream()
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
