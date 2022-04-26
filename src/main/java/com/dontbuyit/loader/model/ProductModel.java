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
public class ProductModel {

    @CsvBindByName(column = "Product Name")
    private String name;

    @CsvBindByName(column = "Brand Name")
    private String brandName;

    @CsvBindByName(column = "Image Url")
    private String imageUrl;
}
