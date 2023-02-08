package com.bootcamp.greencommute.repository;

import com.bootcamp.greencommute.model.ImmutableCity;

import java.util.List;

public interface ICityRepository {
    List<ImmutableCity> getAllCities();
}
