package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.BrandImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandImageService {

  @Autowired
  private CsvLoadingService csvLoadingService;
  @Autowired
  private CsvParsingService csvParsingService;

  public List<BrandImageModel> getBrandImages() {
    final String csvString = csvLoadingService.loadBrandImagesCsv();
    return csvParsingService.parseCsv(csvString, BrandImageModel.class);
  }
}
