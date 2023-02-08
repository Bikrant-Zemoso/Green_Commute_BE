package com.bootcamp.greencommute.controller;

import com.bootcamp.greencommute.model.ImmutableJobDetails;
import com.bootcamp.greencommute.model.ImmutableJobRole;
import com.bootcamp.greencommute.model.ImmutableSavedJob;
import com.bootcamp.greencommute.service.IJobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final IJobService fJobService;

    public JobController(final IJobService jobService){
        this.fJobService = Objects.requireNonNull(jobService,"JobService must not be null");
    }

    @GetMapping("/roles")
    public List<ImmutableJobRole> getAllCollaborator() {
        return fJobService.getAllJobRoles();
    }

    @GetMapping("/search")
    public List<ImmutableJobDetails> getJobDetails(
        @RequestParam(value = "role",required = false) final String role ,
        @RequestParam(value = "location",required = false) final String location,
        @RequestParam(value = "transport", required = false) final List<String> transportOptions,
        @RequestParam(value = "experience", required = false) final List<String> experienceOptions,
        @RequestParam(value = "jobType", required = false) final List<String> jobTypeOptions){
        return fJobService.getJobDetails(role,location,transportOptions,experienceOptions,jobTypeOptions);
    }

    @PostMapping("/save")
    public boolean saveJob(@RequestBody ImmutableSavedJob savedJob){
        final String USER_DETAILS_ID= "132e4567-e89b-12d3-a456-426614174000";
        final ImmutableSavedJob savedJobValue = Objects.requireNonNull(savedJob, "ImmutableSaveJobRequest must not be null");

        return fJobService.saveJob(USER_DETAILS_ID, savedJobValue.getJobDetailsId());
    }

    @GetMapping("/saved")
    public List<ImmutableJobDetails> getSavedJobs() {
        final String USER_DETAILS_ID= "132e4567-e89b-12d3-a456-426614174000";
        return fJobService.getSavedJobs(USER_DETAILS_ID);
    }

}
