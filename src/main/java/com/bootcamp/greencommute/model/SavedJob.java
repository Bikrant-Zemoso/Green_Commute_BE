package com.bootcamp.greencommute.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class SavedJob {

    private static final String USER_DETAILS_ID = "user_details_id";
    private static final String JOB_DETAILS_ID = "job_details_id";

    protected SavedJob() {}

    @JsonProperty(USER_DETAILS_ID)
    public abstract String getUserDetailsId();

    @JsonProperty(JOB_DETAILS_ID)
    public abstract String getJobDetailsId();

}
