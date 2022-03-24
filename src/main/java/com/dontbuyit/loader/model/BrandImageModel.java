package com.dontbuyit.loader.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BrandImageModel {

  @CsvBindByPosition(position = 0)
  private String brandName;

  @CsvBindByPosition(position = 1)
  private String imageUrl;
}
