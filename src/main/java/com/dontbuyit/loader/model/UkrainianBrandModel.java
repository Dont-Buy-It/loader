package com.dontbuyit.loader.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UkrainianBrandModel {

    private static final String COUNTRY = "Ukraine";
    private static final String GRADE = "Ukrainian Brand";

    @CsvBindByName(column = "Brand Name")
    private String name;

    @CsvBindByName(column = "Image Url")
    private String imageUrl;

    public BrandModel toBrandModel() {
        return BrandModel.builder()
                .name(name)
                .logo(imageUrl)
                .country(COUNTRY)
                .grade(GRADE)
                .build();
    }
}
