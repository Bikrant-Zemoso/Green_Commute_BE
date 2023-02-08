package com.bootcamp.greencommute.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class JobRole {
    private static final String ID = "id";
    private static final String ROLE = "role";

    protected JobRole() {}

    @JsonProperty(ID)
    abstract String getId();

    @JsonProperty(ROLE)
    abstract String getRole();
}
