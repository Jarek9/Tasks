package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
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
    private final static Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint()+ "/members/" + trelloConfig.getTrelloUserName() + "/boards")
                .queryParam(KEY, trelloConfig.getTrelloAppKey())
                .queryParam(TOKEN, trelloConfig.getTrelloToken())
                .queryParam(FIELDS, "name,id")
                .queryParam(LISTS, ALL).build().encode().toUri();

        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
            return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam(KEY, trelloConfig.getTrelloAppKey())
                .queryParam(TOKEN, trelloConfig.getTrelloToken())
                .queryParam(NAME, trelloCardDto.getName())
                .queryParam(DESC, trelloCardDto.getDescription())
                .queryParam(POS, trelloCardDto.getPos())
                .queryParam(IDLIST, trelloCardDto.getListId()).build().encode().toUri();
        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }
}
