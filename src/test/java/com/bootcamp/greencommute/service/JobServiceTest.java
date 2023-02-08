package com.bootcamp.greencommute.service;

import com.bootcamp.greencommute.model.*;
import com.bootcamp.greencommute.model.enums.JobType;
import com.bootcamp.greencommute.repository.IJobRepository;
import com.bootcamp.greencommute.repository.impl.JobRepository;
import com.bootcamp.greencommute.service.impl.JobService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    private IJobRepository jobRepository = Mockito.mock(JobRepository.class);

    private IJobService jobService = new JobService(jobRepository);


    // JOB ROLES SERVICE REGION
    @Test
    void testGetAllPosts() {
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

        when(jobRepository.getAllJobRoles()).thenReturn(jobRoles);
        List<ImmutableJobRole> list = jobService.getAllJobRoles();
        Mockito.verify(jobRepository, Mockito.times(1)).getAllJobRoles();
        assertEquals(2, list.size());
        assertEquals(jobRoles, list);
    }
    // END REGION

    // JOB SEARCH SERVICE REGION
    @Test
    void testGetSearchJobDetailsInService() {

        final String role = "Visual Designer";
        final String location = "Hyderabad";
        final List<String> transportOptions = Arrays.asList("Metro","Bus","Cab","Cycle");
        final List<String> jobTypeOptions = Arrays.asList("Internship");
        final List<String> experienceOptions = Arrays.asList("Fresher");

        ImmutableJobDetails jobDetail1 = null;
        try {
            jobDetail1 = ImmutableJobDetails.builder()
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<ImmutableJobDetails> jobDetails = new ArrayList<>();
        jobDetails.add(jobDetail1);


        when(jobRepository.getJobDetails(role, location, transportOptions, jobTypeOptions, experienceOptions)).thenReturn(jobDetails);
        List<ImmutableJobDetails> jobDetailsList = jobService.getJobDetails(role, location, transportOptions, jobTypeOptions, experienceOptions);
        Mockito.verify(jobRepository, Mockito.times(1)).getJobDetails(role, location, transportOptions,
                                                                                                    jobTypeOptions, experienceOptions);
        assertEquals(1, jobDetailsList.size());
        assertEquals(jobDetails, jobDetailsList);
    }
    // END REGION

    // SAVE JOB SERVICE REGION
    @Test
    void testSaveJob() {

        final String USER_DETAILS_ID= "132e4567-e89b-12d3-a456-426614174000";
        final String JOB_DETAILS_ID= "123e4567-e89b-12d3-a456-426614174041";

        when(jobRepository.saveJob(USER_DETAILS_ID, JOB_DETAILS_ID)).thenReturn(true);
        boolean result = jobService.saveJob(USER_DETAILS_ID, JOB_DETAILS_ID);
        Mockito.verify(jobRepository, Mockito.times(1)).saveJob(USER_DETAILS_ID, JOB_DETAILS_ID);

        assertEquals(true, result);
    }
    // END REGION

    // FETCH SAVED JOB SERVICE REGION
    @Test
    void testGetSavedJobDetailsInService() {

        final String USER_DETAILS_ID = "132e4567-e89b-12d3-a456-426614174000";

        ImmutableJobDetails jobDetail1 = null;
        try {
            jobDetail1 = ImmutableJobDetails.builder()
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<ImmutableJobDetails> savedJobDetails = new ArrayList<>();
        savedJobDetails.add(jobDetail1);


        when(jobRepository.getSavedJobs(USER_DETAILS_ID)).thenReturn(savedJobDetails);
        List<ImmutableJobDetails> jobDetailsList = jobService.getSavedJobs(USER_DETAILS_ID);
        Mockito.verify(jobRepository, Mockito.times(1)).getSavedJobs(USER_DETAILS_ID);
        assertEquals(1, jobDetailsList.size());
        assertEquals(savedJobDetails, jobDetailsList);
    }
    // END REGION

}
