package com.bootcamp.greencommute.controller;

import com.bootcamp.greencommute.model.*;
import com.bootcamp.greencommute.model.enums.JobType;
import com.bootcamp.greencommute.service.IJobService;
import com.google.gson.Gson;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
class JobControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IJobService jobService;

    // JOB ROLES API REGION
    @Test
    void testGetAllJobRoles() throws Exception {
        ImmutableJobRole jobRole1 = ImmutableJobRole.builder()
                .id("91823605-9064-4ccc-a113-7ce49b12bab8")
                .role("Visual Designer")
                .build();
        ImmutableJobRole jobRole2 = ImmutableJobRole.builder()
                .id("91823605-9064-4ccc-a113-7ce49b23bab8")
                .role("Product Designer")
                .build();

        List<ImmutableJobRole> jobRoles = new ArrayList<>();
        jobRoles.add(jobRole1);
        jobRoles.add(jobRole2);
        String exampleJSON = "[{\"id\":\"91823605-9064-4ccc-a113-7ce49b12bab8\",\"role\":\"Visual Designer\"}" +
                ",{\"id\":\"91823605-9064-4ccc-a113-7ce49b23bab8\",\"role\":\"Product Designer\"}]";
        Mockito.when(jobService.getAllJobRoles()).thenReturn(jobRoles);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/jobs/roles")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        JSONAssert.assertEquals(exampleJSON, mvcResult.getResponse().getContentAsString(), false);
    }
    // END REGION

    // JOB ROLES API REGION
    @Test
    void testGetSearchJobDetails() throws Exception {

        ImmutableJobDetails jobDetail1 = ImmutableJobDetails.builder()
                                                            .id("JOB_DETAIL_ID")
                                                            .jobRole(ImmutableJobRole.builder()
                                                                    .id("ROLE_ID")
                                                                    .role("Visual Designer")
                                                                    .build())
                                                            .companyDetails(ImmutableCompanyDetails.builder()
                                                                    .id("COMPANY_ID")
                                                                    .companyName("COMPANY_NAME")
                                                                    .icon("COMPANY_ICON")
                                                                    .locationDetails(ImmutableLocationDetails.builder()
                                                                            .id("LOCATION_ID")
                                                                            .name("Hyderabad")
                                                                            .address("Hitech City, Telangana")
                                                                            .city(ImmutableCity.builder()
                                                                                    .id("CITY_ID")
                                                                                    .name("Hyderabad")
                                                                                    .build())
                                                                            .lat(0.0)
                                                                            .getLong(0.0)
                                                                            .build())
                                                                    .isMetroAvailable(true)
                                                                    .isBusAvailable(true)
                                                                    .isCabAvailable(true)
                                                                    .isCycleAvailable(false)
                                                                    .build())
                                                            .experience(0.0)
                                                            .jobType(JobType.getEnum("Internship"))
                                                            .description("Sample")
                                                            .qualifications("")
                                                            .postedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS")
                                                                            .parse("2020-12-18 23:26:32.913232+05:30"))
                                                            .build();

        List<ImmutableJobDetails> jobDetails = new ArrayList<>();
        jobDetails.add(jobDetail1);

        String exampleJSON = "[" +
                "{" +
                    " \"id\": \"JOB_DETAIL_ID\"," +
                    "\"job_role\": {" +
                        " \"id\": \"ROLE_ID\"," +
                        "\"role\": \"Visual Designer\"" +
                    "}," +
                    "\"company_details\": {" +
                        "\"id\": \"COMPANY_ID\"," +
                        "\"company_name\": \"COMPANY_NAME\"," +
                        "\"location_details\": {" +
                            "\"getLong\": 0.0," +
                            "\"id\": \"LOCATION_ID\"," +
                            "\"city\": {\n" +
                                "\"id\": \"CITY_ID\"," +
                            " \"name\": \"Hyderabad\"" +
                            "}," +
                            "\"name\": \"Hyderabad\"," +
                            "\"address\": \"Hitech City, Telangana\"," +
                            "\"lat\": 0.0," +
                            "\"long\": 0.0" +
                        "}," +
                        "\"icon\": \"COMPANY_ICON\"," +
                        "\"is_metro_available\": true," +
                        "\"is_bus_available\": true," +
                        "\"is_cab_available\": true," +
                        "\"is_cycle_available\": false" +
                    "}," +
                    "\"experience\": 0.0," +
                    "\"job_type\": \"INTERNSHIP\"," +
                    "\"description\": \"Sample\"," +
                    "\"qualifications\": \"\"," +
                    "\"posted_at\": \"2020-12-18T23:41:45.232+00:00\"" +
                "}" +
                "]";

        Mockito.when(jobService.getJobDetails(Mockito.anyString(),Mockito.anyString(),
                                                Mockito.anyList(),Mockito.anyList(),Mockito.anyList())).thenReturn(jobDetails);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/jobs/search?role=Visual%20Designer&location=Hyderabad&transport=Metro,Bus,Cab,Cycle&jobType=Internship&experience=Fresher")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        JSONAssert.assertEquals(exampleJSON, mvcResult.getResponse().getContentAsString(), false);
    }
    // END REGION


    // SAVE JOB API REGION
    @Test
    void testSaveJob() throws Exception {
        String exampleJSON = "true";
        final String USER_DETAILS_ID = "132e4567-e89b-12d3-a456-426614174000";
        final String JOB_DETAILS_ID = "123e4567-e89b-12d3-a456-426614174041";
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("user_details_id",USER_DETAILS_ID);
        requestParams.put("job_details_id",JOB_DETAILS_ID);

        Mockito.when(jobService.saveJob(Mockito.anyString(),Mockito.anyString())).thenReturn(true);

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/jobs/save")
                .content(new Gson().toJson(requestParams))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MEDIA_TYPE_JSON_UTF8);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        assertEquals(exampleJSON, mvcResult.getResponse().getContentAsString());
    }
    // END REGION

    // FETCH SAVED JOBS API REGION
    @Test
    void testGetSavedJobDetails() throws Exception {

        ImmutableJobDetails jobDetail1 = ImmutableJobDetails.builder()
                .id("JOB_DETAIL_ID")
                .jobRole(ImmutableJobRole.builder()
                        .id("ROLE_ID")
                        .role("Visual Designer")
                        .build())
                .companyDetails(ImmutableCompanyDetails.builder()
                        .id("COMPANY_ID")
                        .companyName("COMPANY_NAME")
                        .icon("COMPANY_ICON")
                        .locationDetails(ImmutableLocationDetails.builder()
                                .id("LOCATION_ID")
                                .name("Hyderabad")
                                .address("Hitech City, Telangana")
                                .city(ImmutableCity.builder()
                                        .id("CITY_ID")
                                        .name("Hyderabad")
                                        .build())
                                .lat(0.0)
                                .getLong(0.0)
                                .build())
                        .isMetroAvailable(true)
                        .isBusAvailable(true)
                        .isCabAvailable(true)
                        .isCycleAvailable(false)
                        .build())
                .experience(0.0)
                .jobType(JobType.getEnum("Internship"))
                .description("Sample")
                .qualifications("")
                .postedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS")
                        .parse("2020-12-18 23:26:32.913232+05:30"))
                .build();

        List<ImmutableJobDetails> jobDetails = new ArrayList<>();
        jobDetails.add(jobDetail1);

        String exampleJSON = "[" +
                "{" +
                " \"id\": \"JOB_DETAIL_ID\"," +
                "\"job_role\": {" +
                    " \"id\": \"ROLE_ID\"," +
                    "\"role\": \"Visual Designer\"" +
                "}," +
                "\"company_details\": {" +
                    "\"id\": \"COMPANY_ID\"," +
                    "\"company_name\": \"COMPANY_NAME\"," +
                    "\"location_details\": {" +
                        "\"getLong\": 0.0," +
                        "\"id\": \"LOCATION_ID\"," +
                        "\"city\": {\n" +
                        "\"id\": \"CITY_ID\"," +
                        " \"name\": \"Hyderabad\"" +
                    "}," +
                    "\"name\": \"Hyderabad\"," +
                    "\"address\": \"Hitech City, Telangana\"," +
                    "\"lat\": 0.0," +
                    "\"long\": 0.0" +
                    "}," +
                    "\"icon\": \"COMPANY_ICON\"," +
                    "\"is_metro_available\": true," +
                    "\"is_bus_available\": true," +
                    "\"is_cab_available\": true," +
                    "\"is_cycle_available\": false" +
                "}," +
                "\"experience\": 0.0," +
                "\"job_type\": \"INTERNSHIP\"," +
                "\"description\": \"Sample\"," +
                "\"qualifications\": \"\"," +
                "\"posted_at\": \"2020-12-18T23:41:45.232+00:00\"" +
                "}" +
                "]";

        Mockito.when(jobService.getSavedJobs(Mockito.anyString())).thenReturn(jobDetails);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/jobs/saved")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        JSONAssert.assertEquals(exampleJSON, mvcResult.getResponse().getContentAsString(), false);
    }

    // END REGION
}