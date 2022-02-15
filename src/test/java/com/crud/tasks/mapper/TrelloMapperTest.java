package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mappingToDtoTest() {
        //Given
        TrelloList firstTrelloList = new TrelloList("list id", "first list", true);

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(firstTrelloList);

        TrelloCard trelloCard = new TrelloCard("first card", "test", "center", "1");

        TrelloBoard firstTrelloBoard = new TrelloBoard("board id", "first", trelloLists);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(firstTrelloBoard);
        //When
        List<TrelloListDto> trelloDtoList = trelloMapper.mapToListDto(trelloLists);
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoards);
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("list id", trelloDtoList.get(0).getId());
        assertEquals("first list", trelloDtoList.get(0).getName());
        assertEquals("first card", trelloCardDto.getName());
        assertEquals("test", trelloCardDto.getDescription());
        assertEquals("center", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
        assertEquals("board id", trelloBoardDtoList.get(0).getId());
        assertEquals("first", trelloBoardDtoList.get(0).getName());
        assertEquals("list id", trelloBoardDtoList.get(0).getLists().get(0).getId());
        assertEquals("first list", trelloBoardDtoList.get(0).getLists().get(0).getName());
    }

    @Test
    public void mappingFromDtoTest() {
        //Given
        TrelloListDto firstTrelloListDto = new TrelloListDto("list id", "first list", true);

        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(firstTrelloListDto);

        TrelloCardDto trelloCardDto = new TrelloCardDto("first card", "test", "center", "1");

        TrelloBoardDto firstTrelloBoardDto = new TrelloBoardDto("board id", "first", trelloListsDto);

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(firstTrelloBoardDto);
        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListsDto);
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardsDto);
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("list id", trelloList.get(0).getId());
        assertEquals("first list", trelloList.get(0).getName());
        assertEquals("first card", trelloCard.getName());
        assertEquals("test", trelloCard.getDescription());
        assertEquals("center", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
        assertEquals("board id", trelloBoardsDto.get(0).getId());
        assertEquals("first", trelloBoardsDto.get(0).getName());
        assertEquals("list id", trelloBoardsDto.get(0).getLists().get(0).getId());
        assertEquals("first list", trelloBoardsDto.get(0).getLists().get(0).getName());
    }

}