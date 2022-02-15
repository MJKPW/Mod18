package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloConfigTest {

    @Autowired
    private TrelloConfig config;
    @Test
    public void configurationTest() {
        //Given
        //When
        //Then
        Assertions.assertEquals("https://api.trello.com/1", config.getTrelloApiEndpoint());
        Assertions.assertEquals("ec0d992d3d1d7489cbb12b18a8859b10", config.getTrelloAppKey());
        Assertions.assertEquals("bbe1c17daef574fcfde386d9967ba01c0bda13e392db51d40e76c6881f7b9219",
                                                                                    config.getTrelloToken());
        Assertions.assertEquals("michal40418273", config.getTrelloUser());
    }
}