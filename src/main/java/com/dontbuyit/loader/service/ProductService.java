package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

  @Autowired
  private CsvLoadingService csvLoadingService;
  @Autowired
  private CsvParsingService csvParsingService;

  public List<ProductModel> getProducts() {
    final String productsCsv = csvLoadingService.loadProductsCsv();
    return csvParsingService.parseCsv(productsCsv, ProductModel.class).stream()
        .sorted(comparing(ProductModel::getName))
        .collect(toList());
  }
}
