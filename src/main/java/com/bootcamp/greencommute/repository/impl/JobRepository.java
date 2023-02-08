package com.bootcamp.greencommute.repository.impl;

import com.bootcamp.greencommute.model.*;
import com.bootcamp.greencommute.model.enums.JobType;
import com.bootcamp.greencommute.repository.IJobRepository;
import com.bootcamp.greencommute.repository.util.JobDetailsUtil;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class JobRepository implements IJobRepository {

    private final DataSource fDataSource;

    public JobRepository(final DataSource dataSource) {
        fDataSource = Objects.requireNonNull(dataSource, "DataSource must not be null");
    }

    // QUERY AND FIELDS
    private static final String JOB_ROLE_QUERY = "Select * from job_role";
    private static final String JOB_DETAILS_BASE_QUERY = "select " +
            "jd.id, jd.job_role_id, jd.company_id, jd.experience,jd.job_type, jd.description, jd.qualifications,jd.posted_at, " +
            "jr.role, " +
            "cd.company_name, cd.icon, cd.location_id, cd.is_metro_available, cd.is_bus_available, cd.is_cab_available, cd.is_cycle_available, " +
            "ld.name, ld.address,ld.city_id,ld.lat, ld.long, " +
            "ct.name as city_name " +
            "from job_details jd join job_role jr ON jr.id = jd.job_role_id " +
            "join company_details cd ON cd.id=jd.company_id " +
            "join location_details ld ON ld.id=cd.location_id " +
            "join city ct on ct.id=ld.city_id ";
    private static final String SAVE_JOB_BASE_QUERY = "INSERT INTO Saved_Jobs (user_id,job_details_id) VALUES ";
    private static final String FETCH_SAVED_JOBS_BASE_QUERY = "select " +
            "jd.id, jd.job_role_id, jd.company_id, jd.experience,jd.job_type, jd.description, jd.qualifications,jd.posted_at, " +
            "jr.role, " +
            "cd.company_name, cd.icon, cd.location_id, cd.is_metro_available, cd.is_bus_available, cd.is_cab_available, cd.is_cycle_available, " +
            "ld.name, ld.address,ld.city_id,ld.lat, ld.long, " +
            "ct.name as city_name, " +
            "sj.user_id " +
            "from job_details jd join job_role jr ON jr.id = jd.job_role_id " +
            "join company_details cd ON cd.id=jd.company_id " +
            "join location_details ld ON ld.id=cd.location_id " +
            "join city ct on ct.id=ld.city_id "+
            "join saved_jobs sj on sj.job_details_id=jd.id ";

    // JOB DETAILS
    private static final String ID = "id";
    private static final String JOB_ROLE_ID = "job_role_id";
    private static final String COMPANY_ID = "company_id";
    private static final String JOB_TYPE = "job_type";
    private static final String EXPERIENCE = "experience";
    private static final String DESCRIPTION = "description";
    private static final String QUALIFICATIONS = "qualifications";
    private static final String POSTED_AT = "posted_at";

    // JOB ROLE
    private static final String ROLE = "role";

    // COMPANYDETAILS
    private static final String ICON = "icon";
    private static final String COMPANY_NAME = "company_name";
    private static final String LOCATION_ID = "location_id";
    private static final String IS_METRO_AVAILABLE = "is_metro_available";
    private static final String IS_BUS_AVAILABLE = "is_bus_available";
    private static final String IS_CAB_AVAILABLE = "is_cab_available";
    private static final String IS_CYCLE_AVAILABLE = "is_cycle_available";

    // LOCATION
    private static final String CITY_ID = "city_id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String LAT = "lat";
    private static final String LONG = "long";

    // CITIES
    private static final String CITY_NAME = "city_name";


    public List<ImmutableJobRole> getAllJobRoles() {
        Jdbi jdbi = Jdbi.create(fDataSource);

        List<ImmutableJobRole> jobRoles = new ArrayList<>();
        jdbi.useHandle(handle -> {
            final var result = handle.createQuery(JOB_ROLE_QUERY)
                    .mapToMap()
                    .list();
            result.forEach(res -> jobRoles.add(ImmutableJobRole.builder()
                    .id(res.get(ID).toString())
                    .role(res.get(ROLE).toString())
                    .build() ));
        });
        return jobRoles;
    }

    @Override
    public List<ImmutableJobDetails> getJobDetails(String role, String location, List<String> transportOptions,List<String> experienceOptions,List<String> jobTypeOptions) {

        Jdbi jdbi = Jdbi.create(fDataSource);

        final String QUERY_STRING = JOB_DETAILS_BASE_QUERY + JobDetailsUtil.getJobDetailsQueryString(role, location,
                                                                                        transportOptions, experienceOptions, jobTypeOptions);

        List<ImmutableJobDetails> jobDetails = new ArrayList<>();
        jdbi.useHandle(handle -> {
            final var result = handle.createQuery(QUERY_STRING).mapToMap().list();

            // RESULT
            result.forEach(res -> {
               try {
                    jobDetails.add(ImmutableJobDetails.builder()
                                                    .id(res.get(ID).toString())
                                                    .jobRole(ImmutableJobRole.builder()
                                                                            .id(res.get(JOB_ROLE_ID).toString())
                                                                            .role(res.get(ROLE).toString())
                                                                            .build())
                                                    .companyDetails(ImmutableCompanyDetails.builder()
                                                                            .id(res.get(COMPANY_ID).toString())
                                                                            .companyName(res.get(COMPANY_NAME).toString())
                                                                            .icon(res.get(ICON).toString())
                                                                            .locationDetails(ImmutableLocationDetails.builder()
                                                                                                .id(res.get(LOCATION_ID).toString())
                                                                                                .name(res.get(NAME).toString())
                                                                                                .address(res.get(ADDRESS).toString())
                                                                                                .city(ImmutableCity.builder()
                                                                                                        .id(res.get(CITY_ID).toString())
                                                                                                        .name(res.get(CITY_NAME).toString())
                                                                                                        .build())
                                                                                                .lat(Double.parseDouble(res.get(LAT).toString()))
                                                                                                .getLong(Double.parseDouble(res.get(LONG).toString()))
                                                                                                .build())
                                                                            .isMetroAvailable(Boolean.parseBoolean(res.get(IS_METRO_AVAILABLE).toString()))
                                                                            .isBusAvailable(Boolean.parseBoolean(res.get(IS_BUS_AVAILABLE).toString()))
                                                                            .isCabAvailable(Boolean.parseBoolean(res.get(IS_CAB_AVAILABLE).toString()))
                                                                            .isCycleAvailable(Boolean.parseBoolean(res.get(IS_CYCLE_AVAILABLE).toString()))
                                                                            .build())
                                                    .experience(Double.parseDouble(res.get(EXPERIENCE).toString()))
                                                    .jobType(JobType.getEnum(res.get(JOB_TYPE).toString()))
                                                    .description(res.get(DESCRIPTION).toString())
                                                    .qualifications(res.get(QUALIFICATIONS).toString())
                                                    .postedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(res.get(POSTED_AT).toString()))
                                                    .build());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        });
        return jobDetails;
    }

    @Override
    public boolean saveJob(String userDetailsId, String jobDetailsId) {
        Jdbi jdbi = Jdbi.create(fDataSource);

        final boolean[] isJobSaved = {false};

        jdbi.useHandle(handle -> {
            final var result = handle.createQuery("select count(*) as is_job_saved from saved_jobs" +
                    " where job_details_id='"+ jobDetailsId +"' and user_id='"+ userDetailsId +"';").mapToMap().list();
            if(Integer.parseInt(result.get(0).get("is_job_saved").toString()) > 0){
                isJobSaved[0] = true;
            }
        });

        int insertedRowsCount = 0;
        if(!isJobSaved[0]) {
            final String INSERT_QUERY_STRING = SAVE_JOB_BASE_QUERY+"('"+ userDetailsId +"','"+ jobDetailsId +"')";
            insertedRowsCount = jdbi.withHandle(handle -> handle.execute(INSERT_QUERY_STRING));
        }

        return insertedRowsCount == 1;
    }

    @Override
    public List<ImmutableJobDetails> getSavedJobs(String userDetailsId) {

        Jdbi jdbi = Jdbi.create(fDataSource);
        final String QUERY_STRING = FETCH_SAVED_JOBS_BASE_QUERY+"where sj.user_id='"+userDetailsId+"';";

        List<ImmutableJobDetails> savedJobDetails = new ArrayList<>();
        jdbi.useHandle(handle -> {
            final var result = handle.createQuery(QUERY_STRING).mapToMap().list();

            // RESULT
            result.forEach(res -> {
                try {
                    savedJobDetails.add(ImmutableJobDetails.builder()
                            .id(res.get(ID).toString())
                            .jobRole(ImmutableJobRole.builder()
                                    .id(res.get(JOB_ROLE_ID).toString())
                                    .role(res.get(ROLE).toString())
                                    .build())
                            .companyDetails(ImmutableCompanyDetails.builder()
                                    .id(res.get(COMPANY_ID).toString())
                                    .companyName(res.get(COMPANY_NAME).toString())
                                    .icon(res.get(ICON).toString())
                                    .locationDetails(ImmutableLocationDetails.builder()
                                            .id(res.get(LOCATION_ID).toString())
                                            .name(res.get(NAME).toString())
                                            .address(res.get(ADDRESS).toString())
                                            .city(ImmutableCity.builder()
                                                    .id(res.get(CITY_ID).toString())
                                                    .name(res.get(CITY_NAME).toString())
                                                    .build())
                                            .lat(Double.parseDouble(res.get(LAT).toString()))
                                            .getLong(Double.parseDouble(res.get(LONG).toString()))
                                            .build())
                                    .isMetroAvailable(Boolean.parseBoolean(res.get(IS_METRO_AVAILABLE).toString()))
                                    .isBusAvailable(Boolean.parseBoolean(res.get(IS_BUS_AVAILABLE).toString()))
                                    .isCabAvailable(Boolean.parseBoolean(res.get(IS_CAB_AVAILABLE).toString()))
                                    .isCycleAvailable(Boolean.parseBoolean(res.get(IS_CYCLE_AVAILABLE).toString()))
                                    .build())
                            .experience(Double.parseDouble(res.get(EXPERIENCE).toString()))
                            .jobType(JobType.getEnum(res.get(JOB_TYPE).toString()))
                            .description(res.get(DESCRIPTION).toString())
                            .qualifications(res.get(QUALIFICATIONS).toString())
                            .postedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(res.get(POSTED_AT).toString()))
                            .build());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        });

        return savedJobDetails;
    }
}
