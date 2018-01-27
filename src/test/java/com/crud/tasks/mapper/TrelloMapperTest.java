package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void TestMapToBoards() {
        //given
        TrelloListDto trelloListDto1 = new TrelloListDto("Id 1", "List 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("Id 2", "List 2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("Id 3", "List 3", true);

        List<TrelloListDto> trelloListDtos1 = new ArrayList<TrelloListDto>(Arrays.asList(trelloListDto1, trelloListDto2, trelloListDto3));
        List<TrelloListDto> trelloListDtos2 = new ArrayList<TrelloListDto>(Arrays.asList(trelloListDto1, trelloListDto1, trelloListDto1, trelloListDto2, trelloListDto3));
        List<TrelloListDto> trelloListDtos3 = new ArrayList<TrelloListDto>(Arrays.asList(trelloListDto2, trelloListDto3));

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("Board 1", "Table 1", trelloListDtos1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("Board 2", "Table 2", trelloListDtos2);
        TrelloBoardDto trelloBoardDto3 = new TrelloBoardDto("Board 3", "Table 3", trelloListDtos3);

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<TrelloBoardDto>(Arrays.asList(trelloBoardDto1, trelloBoardDto2, trelloBoardDto3));

        //when
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //then
        Assert.assertEquals(3, trelloBoards.size());
        Assert.assertEquals("Board 1", trelloBoards.get(0).getName());
        Assert.assertEquals(5, trelloBoards.get(1).getLists().size());
    }

    @Test
    public void TestMapToBoardsDto() {
        //given
        TrelloList trelloList1 = new TrelloList("Id 1", "List 1", false);
        TrelloList trelloList2 = new TrelloList("Id 2", "List 2", false);
        TrelloList trelloList3 = new TrelloList("Id 3", "List 3", true);

        List<TrelloList> trelloLists1 = new ArrayList<TrelloList>(Arrays.asList(trelloList1, trelloList2, trelloList3));
        List<TrelloList> trelloLists2 = new ArrayList<TrelloList>(Arrays.asList(trelloList1, trelloList1, trelloList1, trelloList2, trelloList3));
        List<TrelloList> trelloLists3 = new ArrayList<TrelloList>(Arrays.asList(trelloList2, trelloList3));

        TrelloBoard trelloBoard1 = new TrelloBoard("Board 1", "Table 1", trelloLists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("Board 2", "Table 2", trelloLists2);
        TrelloBoard trelloBoard3 = new TrelloBoard("Board 3", "Table 3", trelloLists3);

        List<TrelloBoard> trelloBoards = new ArrayList<TrelloBoard>(Arrays.asList(trelloBoard1, trelloBoard2, trelloBoard3));

        //when
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //then
        Assert.assertEquals(true, trelloBoardDtos.get(0).getLists().get(2).isClosed());
        Assert.assertEquals(3, trelloBoards.size());
    }

    @Test
    public void testMapToList() {
        //given
        TrelloListDto trelloListDto1 = new TrelloListDto("Id 1", "List 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("Id 2", "List 2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("Id 3", "List 3", false);
        TrelloListDto trelloListDto4 = new TrelloListDto("Id 4", "List 4", false);

        List<TrelloListDto> trelloListDtos = new ArrayList<TrelloListDto>(Arrays.asList(trelloListDto1, trelloListDto2, trelloListDto3, trelloListDto4));

        //when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //then
        Assert.assertEquals(4, trelloLists.size());
        Assert.assertEquals("List 2", trelloLists.get(1).getName());
    }

    @Test
    public void testMapToListDto(){
        //Given
        TrelloList trelloList1 = new TrelloList("Id1", "list1", true);
        TrelloList trelloList2 = new TrelloList("Id2", "list2", true);
        TrelloList trelloList3 = new TrelloList("Id3", "List3", true);


        List<TrelloList> trelloLists = new ArrayList<TrelloList>(Arrays.asList(trelloList1, trelloList2, trelloList3));

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //then
        Assert.assertEquals(3, trelloListDtos.size());
        Assert.assertEquals(true, trelloLists.get(2).isClosed());
    }

    @Test
    public void testMapToCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(" name1", " description1", "top ", "Id");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertTrue(trelloCard.getName().equals(trelloCardDto.getName()));
        Assert.assertTrue(trelloCard.getDescription().equals(trelloCard.getDescription()));
        Assert.assertTrue(trelloCard.getPos().equals(trelloCardDto.getPos()));
        Assert.assertTrue(trelloCard.getListId().equals(trelloCardDto.getListId()));
        Assert.assertTrue(trelloCard.getName().equals(trelloCardDto.getName()));
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Name 1", "Description 1", "middle", "Id");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertTrue(trelloCardDto.getName().equals(trelloCard.getName()));
        Assert.assertTrue(trelloCardDto.getDescription().equals(trelloCard.getDescription()));
        Assert.assertTrue(trelloCardDto.getPos().equals(trelloCard.getPos()));
        Assert.assertTrue(trelloCardDto.getListId().equals(trelloCard.getListId()));
    }






}
