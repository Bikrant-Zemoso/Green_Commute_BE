package com.bootcamp.greencommute.service;

import com.bootcamp.greencommute.model.ImmutableJobDetails;
import com.bootcamp.greencommute.model.ImmutableJobRole;

import java.util.List;

public interface IJobService {

    List<ImmutableJobRole> getAllJobRoles();

    List<ImmutableJobDetails> getJobDetails(String role, String location,
                                                List<String> transportOptions,List<String> experienceOptions,List<String> jobTypeOptions);

    boolean saveJob(String userDetailsId, String jobDetailsId);

    List<ImmutableJobDetails> getSavedJobs(String userDetailsId);
}
