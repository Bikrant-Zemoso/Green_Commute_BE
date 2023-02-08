package com.bootcamp.greencommute.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class UserDetails {
    private static final String ID = "id";
    private static final String USERNAME = "user_name";
    private static final String PASSWORD = "password";
    private static final String PROFILE_PIC = "profile_pic";

    protected UserDetails() {}

    @JsonProperty("ID")
    abstract String getId();

    @JsonProperty("USERNAME")
    abstract String getUserName();

    @JsonProperty("PASSWORD")
    abstract String getPasssword();

    @JsonProperty("PROFILE_PIC")
    abstract String getProfilePic();
}
