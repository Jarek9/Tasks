package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;
public class TrelloValidatorTest {

    @Test
    public void shouldValidateTrelloCard(){
        //Given
        TrelloCard trelloCard = new TrelloCard("Name", "Descrition","top", "List");
        TrelloValidator validator = new TrelloValidator();
        //When
        validator.validateCard(trelloCard);
        //Then
        Assert.assertTrue("Someone is testing my application",true);

    }
    @Test
    public void shouldValidateTrelloBoards(){
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList list = new TrelloList("Id 1", "Test", false);
        trelloLists.add(list);
        TrelloBoard board1 = new TrelloBoard("Id 1", "Test", trelloLists);
        TrelloBoard board2 = new TrelloBoard("Id 2", "Board 2", trelloLists);
        trelloBoards.add(board1);
        trelloBoards.add(board2);
        TrelloValidator validator = new TrelloValidator();
        //When
        List<TrelloBoard> testList = validator.validateTrelloBoards(trelloBoards);
        //Then
        Assert.assertEquals(1, testList.size());
        Assert.assertEquals("Id 1", testList.get(0).getId());
    }

}
