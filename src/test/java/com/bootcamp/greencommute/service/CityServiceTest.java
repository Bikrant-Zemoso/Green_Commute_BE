package com.bootcamp.greencommute.service;

import com.bootcamp.greencommute.model.ImmutableCity;
import com.bootcamp.greencommute.repository.impl.CityRepository;
import com.bootcamp.greencommute.service.impl.CityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepository citiesRepository;

    @InjectMocks
    private CityService citiesService;

    @Test
    void getAllCities(){
        List<ImmutableCity> cities = new ArrayList<>();
        ImmutableCity cities1 = ImmutableCity.builder()
                .id("123e4567-e89b-12d3-a456-426614174000")
                .name("Mumbai")
                .build();
        ImmutableCity cities2 = ImmutableCity.builder()
                .id("123e4567-e89b-12d3-a456-426614174001")
                .name("Delhi")
                .build();
        cities.add(cities1);
        cities.add(cities2);

        Mockito.when(citiesRepository.getAllCities()).thenReturn(cities);
        List<ImmutableCity> expected = citiesService.getAllCities();
        Mockito.verify(citiesRepository,Mockito.times(1)).getAllCities();
        assertEquals(cities,expected);
        assertEquals(2,expected.size());
    }
}
