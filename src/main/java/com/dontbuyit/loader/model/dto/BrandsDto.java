package com.dontbuyit.loader.model.dto;

import com.dontbuyit.loader.model.BrandModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class BrandsDto {

  private List<BrandModel> brands;

  private ZonedDateTime lastUpdatedTime;
}
