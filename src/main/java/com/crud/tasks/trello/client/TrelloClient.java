package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
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
                .queryParam(FIELDS, NAME,ID).build().encode().toUri();
    }
}
