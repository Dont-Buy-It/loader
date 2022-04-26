package com.dontbuyit.loader.controller;

import com.dontbuyit.loader.model.GradeModel;
import com.dontbuyit.loader.service.GradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GradesControllerTest {

    private final List<GradeModel> gradeModels = List.of();

    @Mock
    private GradeService gradeServiceMock;

    @InjectMocks
    private GradesController gradesController;

    @Test
    void shouldReturnActions() {
        when(gradeServiceMock.getGrades()).thenReturn(gradeModels);

        final List<GradeModel> actualResult = gradesController.getGrades();

        assertEquals(gradeModels, actualResult);
    }
}
