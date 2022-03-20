package com.dontbuyit.loader.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class BrandImageModel {

  @CsvBindByPosition(position = 0)
  private String brandName;

  @CsvBindByPosition(position = 1)
  private String imageUrl;
}
