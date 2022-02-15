package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoardsTest() {
        //Given
        TrelloList list = new TrelloList("list id", "first list", true);
        TrelloBoard board = new TrelloBoard("board id", "test", List.of(list));
        //When
        List<TrelloBoard> boards = List.of(board);
        //Then
        Assertions.assertEquals(0, trelloValidator.validateTrelloBoards(boards).size());
    }

}