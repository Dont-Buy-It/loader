package com.dontbuyit.loader.controller;

import com.dontbuyit.loader.model.ActionModel;
import com.dontbuyit.loader.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/actions")
public class ActionsController {

  @Autowired
  private ActionService actionService;

  @GetMapping
  public List<ActionModel> getActions() {
    return actionService.getActions();
  }
}
