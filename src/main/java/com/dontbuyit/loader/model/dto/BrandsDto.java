package com.dontbuyit.loader.model.dto;

import com.dontbuyit.loader.model.BrandModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BrandsDto {

  private List<BrandModel> brands;

  private ZonedDateTime lastUpdatedTime;
}
