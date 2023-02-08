package com.bootcamp.greencommute.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.immutables.value.Value;

@Value.Immutable
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class UserPreferenceDetails {

    protected UserPreferenceDetails() {};

    abstract ImmutableUserDetails getUserDetails();
    abstract ImmutableLocationDetails getUserLocation();
    abstract ImmutableLocationDetails getPreferredJobLocation();
    abstract ImmutableJobRole getPreferredJobRole();
}
