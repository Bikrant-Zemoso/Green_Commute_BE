package com.bootcamp.greencommute.controller;

import com.bootcamp.greencommute.model.ImmutableCity;
import com.bootcamp.greencommute.service.ICityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class CityController {

    private final ICityService fCityService;

    public CityController(final ICityService fCityService){
        this.fCityService = Objects.requireNonNull(fCityService,"CitiesService can't be null.");
    }

    @GetMapping("/cities")
    public List<ImmutableCity> getAllCollaborator(){
        return fCityService.getAllCities();
    }
}