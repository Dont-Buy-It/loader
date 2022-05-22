package com.dontbuyit.loader.controller;

import com.dontbuyit.loader.model.GradeModel;
import com.dontbuyit.loader.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grades")
public class GradesController {

    private final GradeService gradeService;

    @GetMapping
    public List<GradeModel> getGrades() {
        return gradeService.getGrades();
    }
}
