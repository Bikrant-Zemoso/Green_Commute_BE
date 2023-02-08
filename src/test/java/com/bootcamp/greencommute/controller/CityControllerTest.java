package com.bootcamp.greencommute.controller;

import com.bootcamp.greencommute.model.ImmutableCity;
import com.bootcamp.greencommute.service.ICityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityController.class)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ICityService citiesService;

    @Test
    void testGetAllCities() throws  Exception {
        ImmutableCity cities1 = ImmutableCity.builder()
                .id("123e4567-e89b-12d3-a456-426614174000")
                .name("Mumbai")
                .build();
        ImmutableCity cities2 = ImmutableCity.builder()
                .id("123e4567-e89b-12d3-a456-426614174001")
                .name("Delhi")
                .build();

        List<ImmutableCity> cities = new ArrayList<>();
        cities.add(cities1);
        cities.add(cities2);

        String expectedJson ="[{\"id\":\"123e4567-e89b-12d3-a456-426614174000\",\"name\": \"Mumbai\" }"
                 +",{\"id\":\"123e4567-e89b-12d3-a456-426614174001\",\"name\": \"Delhi\"}]";

        Mockito.when(citiesService.getAllCities()).thenReturn(cities);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cities")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        JSONAssert.assertEquals(expectedJson, mvcResult.getResponse().getContentAsString(), false);
    }
}