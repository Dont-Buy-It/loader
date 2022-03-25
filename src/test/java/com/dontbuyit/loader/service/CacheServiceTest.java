package com.dontbuyit.loader.service;

import org.junit.jupiter.api.Test;

class CacheServiceTest {

  private final CacheService cacheService = new CacheService();

  @Test
  void shouldNotThrowExceptionsOnClearCache() {
    cacheService.clearCache();
  }
}
