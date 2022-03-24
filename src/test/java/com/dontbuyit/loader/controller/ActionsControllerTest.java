package com.dontbuyit.loader.controller;

import com.dontbuyit.loader.model.ActionModel;
import com.dontbuyit.loader.service.ActionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActionsControllerTest {

  private final List<ActionModel> actionModels = List.of();

  @Mock
  private ActionService actionServiceMock;

  @InjectMocks
  private ActionsController actionsController;

  @Test
  void shouldReturnActions() {
    when(actionServiceMock.getActions()).thenReturn(actionModels);

    final List<ActionModel> actualResult = actionsController.getActions();

    assertEquals(actionModels, actualResult);
  }
}
