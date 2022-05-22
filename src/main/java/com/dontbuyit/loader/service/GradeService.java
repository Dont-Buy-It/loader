package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.GradeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final CsvParsingService csvParsingService;

    @Value("${services.external.csv.grades.url}")
    private String gradesCsvUrl;

    @Cacheable(value = "grades")
    public List<GradeModel> getGrades() {
        final List<GradeModel> actionModels = csvParsingService.parseCsv(gradesCsvUrl, GradeModel.class);
        return actionModels.stream()
                .sorted(comparing(GradeModel::getPriority))
                .collect(toList());
    }
}
