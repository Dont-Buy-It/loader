package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.ActionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService {

  @Autowired
  private CsvService csvService;

  public List<ActionModel> getActions() {
    final String csvString = csvService.loadActionsCsv();
    return csvService.parseCsv(csvString, ActionModel.class);
  }
}
