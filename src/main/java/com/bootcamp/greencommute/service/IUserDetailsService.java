package com.bootcamp.greencommute.service;

import com.bootcamp.greencommute.model.ImmutableUserPreferenceDetails;
import java.util.UUID;

public interface IUserDetailsService {
     ImmutableUserPreferenceDetails getAllUserDetails(final UUID userId);
}
