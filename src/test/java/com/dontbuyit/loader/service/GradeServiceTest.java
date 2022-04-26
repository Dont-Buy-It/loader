package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.GradeModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(SpringExtension.class)
class GradeServiceTest {

    private static final String GRADES_CSV_URL = "gradesCsvUrl";

    private final GradeModel gradeModel1 = GradeModel.builder().priority(1).build();
    private final GradeModel gradeModel2 = GradeModel.builder().priority(2).build();
    private final List<GradeModel> gradeModels = List.of(gradeModel2, gradeModel1);

    @Mock
    private CsvParsingService csvParsingServiceMock;

    @InjectMocks
    private GradeService gradeService;

    @Test
    void shouldReturnActionsSortedByPriority() {
        setField(gradeService, "gradesCsvUrl", GRADES_CSV_URL);
        when(csvParsingServiceMock.parseCsv(GRADES_CSV_URL, GradeModel.class)).thenReturn(gradeModels);

        final List<GradeModel> actualResult = gradeService.getGrades();

        assertEquals(List.of(gradeModel1, gradeModel2), actualResult);
    }
}
