package com.bootcamp.greencommute.controller;
import com.bootcamp.greencommute.model.ImmutableUserPreferenceDetails;
import com.bootcamp.greencommute.service.IUserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;

@RestController
public class UserDetailsController {
    private final IUserDetailsService fUserDetailsService;

    public UserDetailsController(final IUserDetailsService userDetailsService){
        this.fUserDetailsService = Objects.requireNonNull(userDetailsService, "UserDetailsService must not be null");
    }

    @GetMapping("/users/{userId}")
    public ImmutableUserPreferenceDetails getUserDetails(@PathVariable final UUID userId) {
        final var id = Objects.requireNonNull(userId, "User Id cannot be null");
        return fUserDetailsService.getAllUserDetails(id);
    }
}
