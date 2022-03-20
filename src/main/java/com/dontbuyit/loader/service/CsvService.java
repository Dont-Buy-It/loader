package com.dontbuyit.loader.service;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.io.StringReader;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Function;

@Service
public class CsvService {

  private static final String CSV_VERSION_QUERY_PARAM = "v";

  @Value("${services.external.csv.brands.url}")
  private String brandsCsvUrl;
  @Value("${services.external.csv.products.url}")
  private String productsCsvUrl;
  @Value("${services.external.csv.actions.url}")
  private String actionsCsvUrl;
  @Value("${services.external.csv.brandImages.url}")
  private String brandImagesCsvUrl;

  @Autowired
  private WebClient webClient;

  public String loadBrandsCsv() {
    final long currentTimeMilliseconds = ZonedDateTime.now().toInstant().toEpochMilli();
    return loadCsv(brandsCsvUrl, uri -> uri.queryParam(CSV_VERSION_QUERY_PARAM, currentTimeMilliseconds).build());
  }

  public String loadProductsCsv() {
    return loadCsv(productsCsvUrl, UriBuilder::build);
  }

  public String loadActionsCsv() {
    return loadCsv(actionsCsvUrl, UriBuilder::build);
  }

  public String loadBrandImagesCsv() {
    return loadCsv(brandImagesCsvUrl, UriBuilder::build);
  }

  public <T> List<T> parseCsv(String csvString, Class<T> type) {
    return new CsvToBeanBuilder<T>(new StringReader(csvString))
        .withType(type)
        .withSkipLines(1)
        .build()
        .parse();
  }

  private String loadCsv(String url, Function<UriBuilder, URI> uriFunction) {
    return webClient
        .get()
        .uri(url, uriFunction)
        .retrieve()
        .bodyToMono(String.class)
        .block();
  }
}
