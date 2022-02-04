package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final String trelloApiEndpoint = "https://api.trello.com/1/members/";
    private final String trelloAppKey = "ec0d992d3d1d7489cbb12b18a8859b10";
    private final String trelloToken = "bbe1c17daef574fcfde386d9967ba01c0bda13e392db51d40e76c6881f7b9219";
    private String trelloUsername = "michal40418273";
    private final RestTemplate restTemplate;
/*
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.api.username}")
    private String trelloUsername;
*/

    private URI buildURL() {

        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + trelloUsername + "/boards")
                                      .queryParam("key", trelloAppKey)
                                      .queryParam("token", trelloToken)
                                      .queryParam("fields", "name,id")
                                      .queryParam("lists", "all")
                                      .build().encode().toUri();
        return url;
    }

    public List<TrelloBoardDto> getTrelloBoards() {

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(buildURL(), TrelloBoardDto[].class);

        if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>();
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                                        .queryParam("key", trelloAppKey)
                                        .queryParam("token", trelloToken)
                                        .queryParam("name", trelloCardDto.getName())
                                        .queryParam("desc", trelloCardDto.getDescription())
                                        .queryParam("pos", trelloCardDto.getPos())
                                        .queryParam("idList", trelloCardDto.getListId())
                                        .build()
                                        .encode()
                                        .toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }

}
