package com.dontbuyit.loader.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.List;

@Service
public class CsvParsingService {

  private static final int HEADER_LINES_AMOUNT = 1;

  public <T> List<T> parseCsv(String csvString, Class<T> type) {
    return new CsvToBeanBuilder<T>(new StringReader(csvString))
        .withType(type)
        .withSkipLines(HEADER_LINES_AMOUNT)
        .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
        .build()
        .parse();
  }
}
