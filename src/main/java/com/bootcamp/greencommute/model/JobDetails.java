package com.bootcamp.greencommute.model;

import com.bootcamp.greencommute.model.enums.JobType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

import java.util.Date;

@Value.Immutable
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class JobDetails {

    private static final String ID = "id";
    private static final String JOB_ROLE = "job_role";
    private static final String COMPANY_DETAILS = "company_details";
    private static final String JOB_TYPE = "job_type";
    private static final String EXPERIENCE = "experience";
    private static final String DESCRIPTION = "description";
    private static final String QUALIFICATIONS = "qualifications";
    private static final String POSTED_AT = "posted_at";

    protected JobDetails() {}

    @JsonProperty(ID)
    abstract String getId();

    @JsonProperty(JOB_ROLE)
    abstract ImmutableJobRole getJobRole();

    @JsonProperty(COMPANY_DETAILS)
    abstract ImmutableCompanyDetails getCompanyDetails();

    @JsonProperty(EXPERIENCE)
    abstract Double getExperience();

    @JsonProperty(JOB_TYPE)
    abstract JobType getJobType();

    @JsonProperty(DESCRIPTION)
    abstract String getDescription();

    @JsonProperty(QUALIFICATIONS)
    abstract String getQualifications();

    @JsonProperty(POSTED_AT)
    abstract Date getPostedAt();
}
