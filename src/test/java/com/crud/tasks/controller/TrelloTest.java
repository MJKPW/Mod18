package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloTest {

    @InjectMocks
    private TrelloController trelloController;

    @Mock
    private TrelloFacade trelloFacade;

    @Test
    public void getTrelloBoardsTest() {
        //Given
        TrelloListDto list = new TrelloListDto("list id", "first list", true);
        TrelloBoardDto board = new TrelloBoardDto("board id", "first", List.of(list));
        //When
        when(trelloFacade.fetchTrelloBoards()).thenReturn(List.of(board));
        //Then
        Assertions.assertEquals(1, trelloController.getTrelloBoards().size());
    }

    @Test
    public void createTrelloCard() {
        //Given
        TrelloCardDto card = new TrelloCardDto("card", "first list", "top", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("test", "test", "test");
        //When
        when(trelloFacade.createCard(card)).thenReturn(createdTrelloCardDto);
        //Then
        Assertions.assertEquals("test", trelloController.createTrelloCard(card).getId());
    }
}