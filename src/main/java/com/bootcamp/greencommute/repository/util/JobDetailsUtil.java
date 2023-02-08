package com.bootcamp.greencommute.repository.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobDetailsUtil {

    protected JobDetailsUtil() {}

    public static String getJobDetailsQueryString(final String role, final String location,
                                          final List<String> transportOptions, final List<String> experienceOptions, final List<String> jobTypeOptions){
        StringBuilder queryParams = new StringBuilder();

        // ROLE
        if(role != null && !"".equals(role)){
            queryParams.append("where lower(jr.role)=lower('" + role + "') ");
        }

        // LOCATION
        if(location != null && !"".equals(location)){
            if (queryParams.length() != 0) {
                queryParams.append("and ");
            } else {
                queryParams.append("where ");
            }
            queryParams.append("lower(ld.name)=lower('").append(location).append("') ");
        }

        // TRANSPORT OPTIONS
        StringBuilder transportParams = new StringBuilder();
        if(transportOptions != null) {
            for (String transportOption : transportOptions) {
                if ("metro".equalsIgnoreCase(transportOption)) {
                    transportParams.append("cd.is_metro_available = true ");
                } else if ("bus".equalsIgnoreCase(transportOption)) {
                    if (transportParams.length() != 0) {
                        transportParams.append("or ");
                    }
                    transportParams.append("cd.is_bus_available = true ");
                } else if ("Car Pooling".equalsIgnoreCase(transportOption)) {
                    if (transportParams.length() != 0) {
                        transportParams.append("or ");
                    }
                    transportParams.append("cd.is_cab_available = true ");
                } else if ("Motor Cycle".equalsIgnoreCase(transportOption)) {
                    if (transportParams.length() != 0) {
                        transportParams.append("or ");
                    }
                    transportParams.append("cd.is_cycle_available = true ");
                }
            }
            if (transportParams.length() != 0) {
                if (queryParams.length() != 0) {
                    queryParams.append("and ");
                } else {
                    queryParams.append("where ");
                }
                queryParams.append("(").append(transportParams).append(") ");
            }
        }

        // EXPERIENCE OPTIONS
        StringBuilder experienceParams = new StringBuilder();
        if(experienceOptions != null) {
            for (String experienceOption : experienceOptions) {
                if ("fresher".equalsIgnoreCase(experienceOption)) {
                    experienceParams.append("jd.experience <= 1 ");
                } else if ("midlevel".equalsIgnoreCase(experienceOption)) {
                    if (experienceParams.length() != 0) {
                        experienceParams.append("or ");
                    }
                    experienceParams.append("jd.experience <= 2");
                } else if ("executive".equalsIgnoreCase(experienceOption)) {
                    if (experienceParams.length() != 0) {
                        experienceParams.append("or ");
                    }
                    experienceParams.append("jd.experience <= 4");
                } else if ("director".equalsIgnoreCase(experienceOption)) {
                    if (experienceParams.length() != 0) {
                        experienceParams.append("or ");
                    }
                    experienceParams.append("jd.experience <= 6");
                }
            }
            if (experienceParams.length() != 0) {
                if (queryParams.length() != 0) {
                    queryParams.append("and ");
                } else {
                    queryParams.append("where ");
                }
                queryParams.append("(").append(experienceParams).append(") ");
            }
        }

        // JOBTYPE OPTIONS
        StringBuilder jobTypeParams = new StringBuilder();
        if(jobTypeOptions != null) {
            for (String jobTypeOption : jobTypeOptions) {
                if (jobTypeParams.length() != 0) {
                    jobTypeParams.append("or ");
                }
                jobTypeParams.append("lower(jd.job_type) = ").append("lower('").append(jobTypeOption).append("') ");
            }
            if (jobTypeParams.length() != 0) {
                if (queryParams.length() != 0) {
                    queryParams.append("and ");
                } else {
                    queryParams.append("where ");
                }
                queryParams.append("(").append(jobTypeParams).append(") ");
            }
        }


        return queryParams.toString();
    }

}
