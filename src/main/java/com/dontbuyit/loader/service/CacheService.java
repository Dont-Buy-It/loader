package com.dontbuyit.loader.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

  @Scheduled(cron = "${cache.cron}")
  @CacheEvict(value = {"brands", "brandImages", "products", "actions"}, allEntries = true)
  public void clearCache() {
  }
}
