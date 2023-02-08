package com.bootcamp.greencommute.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class LocationDetails {

    private static final String ID = "id";
    private static final String CITY = "city";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String LAT = "lat";
    private static final String LONG = "long";

    protected LocationDetails() {}

    @JsonProperty(ID)
    abstract String getId();

    @JsonProperty(CITY)
    abstract ImmutableCity getCity();

    @JsonProperty(NAME)
    abstract String getName();

    @JsonProperty(ADDRESS)
    abstract String getAddress();

    @JsonProperty(LAT)
    abstract Double getLat();

    @JsonProperty(LONG)
    abstract Double getLong();
}