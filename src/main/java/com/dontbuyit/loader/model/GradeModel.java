package com.dontbuyit.loader.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GradeModel {

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "Display Name")
    private String displayName;

    @CsvBindByName(column = "Description")
    private String description;

    @CsvBindByName(column = "Color")
    private String color;

    @CsvBindByName(column = "Priority")
    private int priority;

    @CsvBindByName(column = "Is Available for Purchase")
    private boolean isAvailableForPurchase;
}
