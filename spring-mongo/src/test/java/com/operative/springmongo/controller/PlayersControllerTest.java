package com.operative.springmongo.controller;

import com.operative.springmongo.model.Player;
import com.operative.springmongo.model.Team;
import com.operative.springmongo.service.serviceInterface.PlayersService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PlayersController.class)
public class PlayersControllerTest {

    @Autowired MockMvc mockMvc;
    @MockBean PlayersService playersService;

    Team team=new Team("000000000001","Liverpool","England","English Premiere League");
    Player mockPlayer=new Player("00001","Mo Salah","Egypt",30,team);

    String examplePlayer="{\n" +
            "    \"id\": \"63295fe5da46ad74ecf82ee1\",\n" +
            "    \"name\": \"Robert Lewandowski\",\n" +
            "    \"nationality\": \"Poland\",\n" +
            "    \"age\": 34,\n" +
            "    \"team\": {\n" +
            "        \"id\": \"63295fe5da46ad74ecf82ee0\",\n" +
            "        \"name\": \"Barcelona\",\n" +
            "        \"country\": \"Spain\",\n" +
            "        \"league\": \"La Liga\"\n" +
            "    }\n" +
            "}";

    @Test
    public void getAllPlayers() {
    }

    @Test
    public void getPlayerDetails() throws Exception {
        Mockito.when(playersService.getPlayerById(Mockito.anyString())).thenReturn(mockPlayer);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/player/63295fe5da46ad74ecf82ee1").accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
//        String expected=
        JSONAssert.assertEquals(examplePlayer,result.getResponse().getContentAsString(),false);
    }

    @Test
    public void addPlayer() {
    }
}