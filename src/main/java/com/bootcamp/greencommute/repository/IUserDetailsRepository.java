package com.bootcamp.greencommute.repository;

import com.bootcamp.greencommute.model.ImmutableUserDetails;
import com.bootcamp.greencommute.model.ImmutableUserPreferenceDetails;

import java.util.List;
import java.util.UUID;

public interface IUserDetailsRepository {
    List<ImmutableUserPreferenceDetails> getAllUserDetails(UUID uuid);
}
