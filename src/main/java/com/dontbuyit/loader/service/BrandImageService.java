package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.BrandImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandImageService {

  @Autowired
  private CsvService csvService;

  public List<BrandImageModel> getBrandImages() {
    final String csvString = csvService.loadBrandImagesCsv();
    return csvService.parseCsv(csvString, BrandImageModel.class);
  }
}
