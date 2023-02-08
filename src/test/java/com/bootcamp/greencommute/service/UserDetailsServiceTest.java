package com.bootcamp.greencommute.service;

import com.bootcamp.greencommute.model.*;
import com.bootcamp.greencommute.repository.impl.UserDetailsRepository;
import com.bootcamp.greencommute.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceTest {
    @Mock
    private UserDetailsRepository userDetailsRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void getUserDetailsTest(){
        UUID id  = UUID.fromString("132e4567-e89b-12d3-a456-426614174000");

        ImmutableUserPreferenceDetails userPreferenceDetails = ImmutableUserPreferenceDetails.builder()
                .userLocation(ImmutableLocationDetails.builder()
                        .id("123e4567-e89b-12d3-a456-426614174011")
                        .address("Maharastra, India")
                        .name("Mumbai")
                        .lat(19.076)
                        .getLong(72.8777)
                        .city(ImmutableCity.builder()
                                .id("123e4567-e89b-12d3-a456-426614174000")
                                .name("Mumbai")
                                .build())
                        .build())
                .preferredJobLocation(ImmutableLocationDetails.builder()
                        .id("123e4567-e89b-12d3-a456-426614174012")
                        .address("Delhi, India")
                        .name("Delhi")
                        .lat(28.7041)
                        .getLong(77.1025)
                        .city(ImmutableCity.builder()
                                .id("123e4567-e89b-12d3-a456-426614174001")
                                .name("Delhi")
                                .build())
                        .build())
                .userDetails(ImmutableUserDetails.builder()
                        .id("132e4567-e89b-12d3-a456-426614174000")
                        .passsword("zemoso123")
                        .profilePic("")
                        .userName("Ram")
                        .build())
                .preferredJobRole(ImmutableJobRole.builder()
                        .role("User Experience Designer")
                        .id("92fbd158-78bc-4017-866d-27a05767fc5e")
                        .build())
                .build();

        Mockito.when(userDetailsRepository.getAllUserDetails(id)).thenReturn(Collections.singletonList(userPreferenceDetails));
        ImmutableUserPreferenceDetails expected = userDetailsService.getAllUserDetails(id);
        Mockito.verify(userDetailsRepository,Mockito.times(1)).getAllUserDetails(id);
        assertEquals(userPreferenceDetails,expected);
    }
}
