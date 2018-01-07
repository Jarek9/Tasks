package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    private final static String KEY = "key";
    private final static String TOKEN = "token";
    private final static String FIELDS = "fields";
    private final static String NAME = "name";
    private final static String ID = "id";
    private final static String LISTS = "lists";
    private final static String ALL = "all";
    private final static String DESC = "desc";
    private final static String POS = "pos";
    private final static String IDLIST = "idList";

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUserName;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {


        Optional<TrelloBoardDto[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(getUri(),
                TrelloBoardDto[].class));
            return boardsResponse.map(Arrays::asList).orElseGet(ArrayList::new);
    }

    private URI getUri() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUserName + "/boards")
                .queryParam(KEY, trelloAppKey)
                .queryParam(TOKEN, trelloToken)
                .queryParam(FIELDS, NAME,ID)
                .queryParam(LISTS, ALL).build().encode().toUri();
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam(KEY, trelloAppKey)
                .queryParam(TOKEN, trelloToken)
                .queryParam(NAME, trelloCardDto.getName())
                .queryParam(DESC, trelloCardDto.getDescription())
                .queryParam(POS, trelloCardDto.getPos())
                .queryParam(IDLIST, trelloCardDto.getListId()).build().encode().toUri();
        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }
}
