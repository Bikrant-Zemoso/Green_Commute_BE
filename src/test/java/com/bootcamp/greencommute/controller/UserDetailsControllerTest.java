package com.bootcamp.greencommute.controller;

import com.bootcamp.greencommute.model.*;
import com.bootcamp.greencommute.service.IUserDetailsService;
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
import java.util.UUID;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserDetailsController.class)
class UserDetailsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IUserDetailsService userDetailsService;

    @Test
    void getUserDetailsTest() throws Exception{
        UUID id  = UUID.fromString("132e4567-e89b-12d3-a456-426614174000");
        ImmutableUserPreferenceDetails userPreferenceDetails = ImmutableUserPreferenceDetails.builder()
                .userLocation(ImmutableLocationDetails.builder()
                        .id("123e4567-e89b-12d3-a456-426614174011")
                        .address("Maharastra, India")
                        .name("Mumbai")
                        .lat(19.076)
                        .getLong(72.8777)
                        .city(ImmutableCity.builder()
                                .id("123e4567-e89b-12d3-a456-426614174000")
                                .name("Mumbai")
                                .build())
                        .build())
                .preferredJobLocation(ImmutableLocationDetails.builder()
                        .id("123e4567-e89b-12d3-a456-426614174012")
                        .address("Delhi, India")
                        .name("Delhi")
                        .lat(28.7041)
                        .getLong(77.1025)
                        .city(ImmutableCity.builder()
                                .id("123e4567-e89b-12d3-a456-426614174001")
                                .name("Delhi")
                                .build())
                        .build())
                .userDetails(ImmutableUserDetails.builder()
                        .id("132e4567-e89b-12d3-a456-426614174000")
                        .passsword("zemoso123")
                        .profilePic("")
                        .userName("Ram")
                        .build())
                .preferredJobRole(ImmutableJobRole.builder()
                        .role("User Experience Designer")
                        .id("92fbd158-78bc-4017-866d-27a05767fc5e")
                        .build())
                .build();
        String expectedJson = "{ "+
                " \"userDetails\":{" +
                    "\"ID\":\"132e4567-e89b-12d3-a456-426614174000\"," +
                    "\"USERNAME\":\"Ram\"," +
                    "\"PASSWORD\":\"zemoso123\"," +
                    "\"PROFILE_PIC\":\"\"" +
                "}," +
                "\"userLocation\":{" +
                    "\"getLong\":72.8777," +
                    "\"id\":\"123e4567-e89b-12d3-a456-426614174011\"," +
                    "\"city\":{" +
                        "\"id\":\"123e4567-e89b-12d3-a456-426614174000\"," +
                        "\"name\":\"Mumbai\"" +
                    "}," +
                    "\"name\":\"Mumbai\"," +
                    "\"address\":\"Maharastra, India\"," +
                    "\"lat\":19.076," +
                    "\"long\":72.8777" +
                "}," +
                "\"preferredJobLocation\":{" +
                    "\"getLong\":77.1025," +
                    "\"id\":\"123e4567-e89b-12d3-a456-426614174012\"," +
                    "\"city\":{" +
                        "\"id\":\"123e4567-e89b-12d3-a456-426614174001\"," +
                        "\"name\":\"Delhi\"" +
                    "}," +
                    "\"name\":\"Delhi\"," +
                    "\"address\":\"Delhi, India\"," +
                    "\"lat\":28.7041," +
                    "\"long\":77.1025" +
                "}," +
                "\"preferredJobRole\":{" +
                    "\"id\":\"92fbd158-78bc-4017-866d-27a05767fc5e\"," +
                    "\"role\":\"User Experience Designer\"" +
                "} }";
        Mockito.when(userDetailsService.getAllUserDetails(id)).thenReturn(userPreferenceDetails);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/132e4567-e89b-12d3-a456-426614174000")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        JSONAssert.assertEquals(expectedJson, mvcResult.getResponse().getContentAsString(), false);
    }
}
