package com.dontbuyit.loader.service;

import com.dontbuyit.loader.model.ActionModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActionServiceTest {

  private static final String ACTIONS_CSV_STRING = "actionsCsvString";

  private final ActionModel actionModel1 = ActionModel.builder().priority(1).build();
  private final ActionModel actionModel2 = ActionModel.builder().priority(2).build();
  private final List<ActionModel> actionModels = List.of(actionModel2, actionModel1);

  @Mock
  private CsvLoadingService csvLoadingServiceMock;
  @Mock
  private CsvParsingService csvParsingServiceMock;

  @InjectMocks
  private ActionService actionService;

  @Test
  void shouldReturnActionsSortedByPriority() {
    when(csvLoadingServiceMock.loadActionsCsv()).thenReturn(ACTIONS_CSV_STRING);
    when(csvParsingServiceMock.parseCsv(ACTIONS_CSV_STRING, ActionModel.class)).thenReturn(actionModels);

    final List<ActionModel> actualResult = actionService.getActions();

    assertEquals(List.of(actionModel1, actionModel2), actualResult);
  }
}
