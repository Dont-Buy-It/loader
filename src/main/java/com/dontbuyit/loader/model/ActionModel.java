package com.dontbuyit.loader.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class ActionModel {

  @CsvBindByPosition(position = 0)
  private String name;

  @CsvBindByPosition(position = 1)
  private String displayName;

  @CsvBindByPosition(position = 2)
  private String description;

  @CsvBindByPosition(position = 3)
  private String color;

  @CsvBindByPosition(position = 4)
  private int priority;

  @CsvBindByPosition(position = 5)
  private boolean isAvailableForPurchase;
}
