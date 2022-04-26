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
public class BrandModel {

    @CsvBindByName(column = "Translated Country")
    private String translatedCountry;

    @CsvBindByName(column = "Translated Action")
    private String translatedAction;

    @CsvBindByName(column = "Translated GICS Industry Sector")
    private String translatedGicsIndustrySector;

    @CsvBindByName(column = "Translated Magnitude of Russian Operations")
    private String translatedMagnitudeOfOccupantOperations;

    @CsvDate("yyyy/MM/dd h:mm a")
    @CsvBindByName(column = "Row Updated At")
    private LocalDateTime rowUpdatedAt;

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "Logo")
    private String logo;

    @CsvBindByName(column = "Grade")
    private String grade;

    @CsvBindByName(column = "Country")
    private String country;

    @CsvBindByName(column = "Action")
    private String action;

    @CsvDate("yyyy-MM-dd")
    @CsvBindByName(column = "Date of Last Action")
    private LocalDate dateOfLastAction;

    @CsvBindByName(column = "Link to Announcement")
    private String linkToAnnouncement;

    @CsvBindByName(column = "GICS Industry Sector")
    private String gicsIndustrySector;

    @CsvBindByName(column = "Magnitude of Russian Operations")
    private String magnitudeOfOccupantOperations;

    private List<ProductModel> products;
}
