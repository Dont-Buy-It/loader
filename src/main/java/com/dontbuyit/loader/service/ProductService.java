package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CsvParsingService csvParsingService;

    @Value("${services.external.csv.products.url}")
    private String productsCsvUrl;

    @Cacheable(value = "products")
    public List<ProductModel> getProducts() {
        return csvParsingService.parseCsv(productsCsvUrl, ProductModel.class).stream()
                .filter(productModel -> nonNull(productModel.getName()))
                .filter(productModel -> nonNull(productModel.getBrandName()))
                .sorted(comparing(ProductModel::getName))
                .collect(toList());
    }
}
