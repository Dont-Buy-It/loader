package com.dontbuyit.loader.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BrandModel {

  @CsvBindByPosition(position = 0)
  private String name;

  @CsvBindByPosition(position = 1)
  private String action;

  @CsvBindByPosition(position = 2)
  private String notes;

  @CsvBindByPosition(position = 3)
  private String actionEsp;

  private String imageUrl;

  private List<ProductModel> productModels;
}
