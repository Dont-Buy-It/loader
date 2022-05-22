package com.dontbuyit.loader.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final BrandService brandService;
    private final GradeService gradeService;

    @Scheduled(cron = "${cache.cron}")
    @CacheEvict(value = {"brands", "products", "grades"}, allEntries = true)
    public void refreshCache() {
        createCache();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createCache() {
        gradeService.getGrades();
        brandService.getBrands();
    }
}
