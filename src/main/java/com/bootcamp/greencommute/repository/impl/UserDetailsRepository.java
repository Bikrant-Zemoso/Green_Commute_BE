package com.bootcamp.greencommute.repository.impl;

import com.bootcamp.greencommute.model.ImmutableCity;
import com.bootcamp.greencommute.model.*;
import com.bootcamp.greencommute.model.ImmutableUserDetails;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class UserDetailsRepository implements com.bootcamp.greencommute.repository.IUserDetailsRepository {
    private final DataSource fDataSource;
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String PROFILE_PIC = "profile_pic";

    private static final String LOCATION_ID = "location_id";
    private static final String CITY_ID = "city_id";
    private static final String CITY_NAME = "city_name";
    private static final String LOCATION_NAME = "location_name";
    private static final String ADDRESS = "address";
    private static final String LAT = "lat";
    private static final String LONG = "long";

    private static final String P_LOCATION_ID = "p_location_id";
    private static final String P_CITY_ID = "p_city_id";
    private static final String P_CITY_NAME = "p_city_name";
    private static final String P_LOCATION_NAME = "p_location_name";
    private static final String P_ADDRESS = "p_address";
    private static final String P_LAT = "p_lat";
    private static final String P_LONG = "p_long";

    private static final String JOB_ROLE_ID = "job_role_id";
    private static final String ROLE = "role";

    public UserDetailsRepository(final DataSource dataSource){
        this.fDataSource = dataSource;
    }

    public List<ImmutableUserPreferenceDetails> getAllUserDetails(final UUID uuid) {
        final var id = Objects.requireNonNull(uuid, "User Id cannot be null");
        //Query
        final String USER_DETAILS_QUERY =
                "Select * from " +
                        "(Select ud.id, ud.username,ud.password, ud.profile_pic, " +
                        "ld.id As location_id, ld.name As location_name, ld.address, ld.lat, ld.long, " +
                        "jr.id As job_role_id, jr.role, ct.name AS city_name, ct.id As city_id from " +
                        "user_details ud join user_preference up on ud.id = up.user_details_id " +
                        "join location_details ld on up.user_location_id = ld.id " +
                        "join user_preference_job_role upjr on upjr.user_preference_id = up.id " +
                        "join Job_Role jr on upjr.job_role_id = jr.id " +
                        "join city ct on ld.city_id = ct.id " +
                        "where ud.id = '" + id + "') user_basic_details " +
                        "join " +
                        "(Select ud.id, " +
                        "ld.id As p_location_id, ld.name As p_location_name, ld.address As p_address, " +
                        "ld.lat As p_lat, ld.long As p_long, " +
                        "ct.name AS p_city_name, ct.id As p_city_id from " +
                        "user_details ud join user_preference up on ud.id = up.user_details_id " +
                        "join user_preference_job_location upjl on upjl.user_preference_id = up.id " +
                        "join location_details ld on upjl.location_id = ld.id " +
                        "join city ct on ld.city_id = ct.id " +
                        "where ud.id = '"+ id + "') user_pref_location " +
                        "on " +
                        "user_basic_details.id = user_pref_location.id ";
        Jdbi jdbi = Jdbi.create(this.fDataSource);
        List<ImmutableUserPreferenceDetails> userDetails = new ArrayList<>();
        jdbi.useHandle(handle -> {
            final var result = handle.createQuery(USER_DETAILS_QUERY)
                    .mapToMap()
                    .list();
            result.forEach(res -> {
                userDetails.add(ImmutableUserPreferenceDetails.builder()
                        .userDetails(ImmutableUserDetails.builder()
                                .id(res.get(ID).toString())
                                .userName(res.get(USERNAME).toString())
                                .passsword(res.get(PASSWORD).toString())
                                .profilePic(res.get(PROFILE_PIC).toString())
                                .build())
                        .userLocation(ImmutableLocationDetails.builder()
                                .id(res.get(LOCATION_ID).toString())
                                .city(ImmutableCity.builder()
                                        .id(res.get(CITY_ID).toString())
                                        .name(res.get(CITY_NAME).toString())
                                        .build())
                                .name(res.get(LOCATION_NAME).toString())
                                .address(res.get(ADDRESS).toString())
                                .lat(Double.parseDouble(res.get(LAT).toString()))
                                .getLong(Double.parseDouble(res.get(LONG).toString()))
                                .build())
                        .preferredJobLocation(ImmutableLocationDetails.builder()
                                .id(res.get(P_LOCATION_ID).toString())
                                .city(ImmutableCity.builder()
                                        .id(res.get(P_CITY_ID).toString())
                                        .name(res.get(P_CITY_NAME).toString())
                                        .build())
                                .name(res.get(P_LOCATION_NAME).toString())
                                .address(res.get(P_ADDRESS).toString())
                                .lat(Double.parseDouble(res.get(P_LAT).toString()))
                                .getLong(Double.parseDouble(res.get(P_LONG).toString()))
                                .build())
                        .preferredJobRole(ImmutableJobRole.builder()
                                .id(res.get(JOB_ROLE_ID).toString())
                                .role(res.get(ROLE).toString())
                                .build())
                        .build());
            });
        });
        return userDetails;
    }
}
