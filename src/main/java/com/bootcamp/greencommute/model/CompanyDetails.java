package com.bootcamp.greencommute.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

@Value.Immutable
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class CompanyDetails {

    private static final String ID = "id";
    private static final String COMPANY_NAME = "company_name";
    private static final String LOCATION_DETAILS = "location_details";
    private static final String ICON = "icon";
    private static final String IS_METRO_AVAILABLE = "is_metro_available";
    private static final String IS_BUS_AVAILABLE = "is_bus_available";
    private static final String IS_CAB_AVAILABLE = "is_cab_available";
    private static final String IS_CYCLE_AVAILABLE = "is_cycle_available";

    protected CompanyDetails() {}

    @JsonProperty(ID)
    abstract String getId();

    @JsonProperty(COMPANY_NAME)
    abstract String getCompanyName();

    @JsonProperty(LOCATION_DETAILS)
    abstract ImmutableLocationDetails getLocationDetails();

    @JsonProperty(ICON)
    abstract String getIcon();

    @JsonProperty(IS_METRO_AVAILABLE)
    abstract Boolean getIsMetroAvailable();

    @JsonProperty(IS_BUS_AVAILABLE)
    abstract Boolean getIsBusAvailable();

    @JsonProperty(IS_CAB_AVAILABLE)
    abstract Boolean getIsCabAvailable();

    @JsonProperty(IS_CYCLE_AVAILABLE)
    abstract Boolean getIsCycleAvailable();
}
