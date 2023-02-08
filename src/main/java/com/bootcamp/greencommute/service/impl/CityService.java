package com.bootcamp.greencommute.service.impl;

import com.bootcamp.greencommute.exception.ResourceNotFoundException;
import com.bootcamp.greencommute.model.ImmutableCity;
import com.bootcamp.greencommute.repository.ICityRepository;
import com.bootcamp.greencommute.service.ICityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CityService implements ICityService {

    private final ICityRepository fCityRepository;

    public CityService(final ICityRepository fCityRepository){
        this.fCityRepository = Objects.requireNonNull(fCityRepository,"CitiesRepository can't be null.");
    }

    @Override
    public List<ImmutableCity> getAllCities() {
        List<ImmutableCity> cities = fCityRepository.getAllCities();
        if(cities.isEmpty()){
            log.error("No data found for cities");
            throw new ResourceNotFoundException("No data found for cities.");
        }
        return cities;
    }
}
