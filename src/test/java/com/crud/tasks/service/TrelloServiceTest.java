package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void fetchTrelloBoardsTest() {
        //Given
        TrelloListDto list = new TrelloListDto("list id", "first list", true);
        TrelloBoardDto board = new TrelloBoardDto("board id", "first", List.of(list));
        //Then
        when(trelloClient.getTrelloBoards()).thenReturn(List.of(board));
        //When
        Assertions.assertEquals(1, trelloService.fetchTrelloBoards().size());
    }

    @Test
    public void createTrelloCardTest() {
        //Given
        TrelloCardDto card = new TrelloCardDto("card", "first list", "top", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("test", "test", "test");
        //When
        when(trelloClient.createNewCard(card)).thenReturn(createdTrelloCardDto);
        //Then
        Assertions.assertThrows(RuntimeException.class, ()-> {
            trelloService.createTrelloCard(card).getId();
        });
    }
}