package com.bootcamp.greencommute.service.impl;

import com.bootcamp.greencommute.exception.ResourceNotFoundException;
import com.bootcamp.greencommute.model.ImmutableUserPreferenceDetails;
import com.bootcamp.greencommute.repository.impl.UserDetailsRepository;
import com.bootcamp.greencommute.service.IUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class UserDetailsServiceImpl implements IUserDetailsService {
    private final UserDetailsRepository fUserDetailsRepository;

    public UserDetailsServiceImpl(final UserDetailsRepository userDetailsRepository){
        this.fUserDetailsRepository = Objects.requireNonNull(userDetailsRepository, "UserDetailsRepository must not be null");
    }

    @Override
    public ImmutableUserPreferenceDetails getAllUserDetails(final UUID uuid) {
        final var id = Objects.requireNonNull(uuid, "User Id cannot be null");
        List<ImmutableUserPreferenceDetails> userDetailsList = fUserDetailsRepository.getAllUserDetails(id);
        if(userDetailsList.isEmpty()){
            log.error("User with userId - {} not found ", uuid);
            throw new ResourceNotFoundException("User with userId " + uuid + " not found. " );
        }
        return userDetailsList.get(0);
    }
}
