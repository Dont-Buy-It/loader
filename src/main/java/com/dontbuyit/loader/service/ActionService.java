package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.ActionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
public class ActionService {

  @Autowired
  private CsvLoadingService csvLoadingService;
  @Autowired
  private CsvParsingService csvParsingService;

  @Cacheable(value = "actions")
  public List<ActionModel> getActions() {
    final String csvString = csvLoadingService.loadActionsCsv();
    final List<ActionModel> actionModels = csvParsingService.parseCsv(csvString, ActionModel.class);
    return actionModels.stream()
        .sorted(comparing(ActionModel::getPriority))
        .collect(toList());
  }
}
