package com.dontbuyit.loader.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.StringReader;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvParsingService {

    private static final long RETRIES_AMOUNT = 5;
    private static final long TIMEOUT_SECONDS = 30;

    private final WebClient webClient;

    public <T> List<T> parseCsv(String url, Class<T> type) {
        final String csvString = loadCsv(url);
        return new CsvToBeanBuilder<T>(new StringReader(csvString))
                .withType(type)
                .withFieldAsNull(CSVReaderNullFieldIndicator.BOTH)
                .build()
                .parse();
    }

    private String loadCsv(String url) {
        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .retry(RETRIES_AMOUNT)
                .timeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .block();
    }
}
