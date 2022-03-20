package com.dontbuyit.loader.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class ProductModel {

  @CsvBindByPosition(position = 0)
  private String name;

  @CsvBindByPosition(position = 1)
  private String brandName;

  @CsvBindByPosition(position = 2)
  private String imageUrl;
}
