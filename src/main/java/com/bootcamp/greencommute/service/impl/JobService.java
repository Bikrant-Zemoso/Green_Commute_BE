package com.bootcamp.greencommute.service.impl;

import com.bootcamp.greencommute.exception.BadRequestException;
import com.bootcamp.greencommute.exception.ResourceNotFoundException;
import com.bootcamp.greencommute.model.ImmutableJobDetails;
import com.bootcamp.greencommute.model.ImmutableJobRole;
import com.bootcamp.greencommute.repository.IJobRepository;
import com.bootcamp.greencommute.service.IJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class JobService implements IJobService {

    private final IJobRepository fJobRepository;

    public JobService(final IJobRepository jobRepository){
        this.fJobRepository = Objects.requireNonNull(jobRepository,"JobRepository must not be null");
    }

    @Override
    public List<ImmutableJobRole> getAllJobRoles() {
        List<ImmutableJobRole> jobRoles = fJobRepository.getAllJobRoles();
        if(jobRoles.isEmpty()){
            log.error("No data found for job roles");
            throw new ResourceNotFoundException("No data found for job roles");
        }
        return jobRoles;
    }

    @Override
    public List<ImmutableJobDetails> getJobDetails(
        final String role, final String location, final List<String> transportOptions,
        final List<String> experienceOptions, final List<String> jobTypeOptions) {
        List<ImmutableJobDetails> jobDetails = fJobRepository.getJobDetails(role, location, transportOptions, experienceOptions, jobTypeOptions);
        if(jobDetails.isEmpty()){
            log.error("No data found for job details");
            throw  new ResourceNotFoundException("No data found for job details");
        }
        return jobDetails;
    }

    @Override
    public boolean saveJob(String userDetailsId, String jobDetailsId) {
        boolean savedJobs = fJobRepository.saveJob(userDetailsId, jobDetailsId);
        if(!savedJobs){
            log.error("Invalid Json unable to save");
            throw new BadRequestException("Bad Json for save job.");
        }
        return savedJobs;
    }

    @Override
    public List<ImmutableJobDetails> getSavedJobs(String userDetailsId) {
        List<ImmutableJobDetails> jobDetails = fJobRepository.getSavedJobs(userDetailsId);
        if(jobDetails.isEmpty()){
            log.error("No data found for saved jobs");
            throw  new ResourceNotFoundException("No data found for saved jobs");
        }
        return jobDetails;
    }
}
