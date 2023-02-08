package com.bootcamp.greencommute.repository.impl;

import com.bootcamp.greencommute.model.ImmutableCity;
import com.bootcamp.greencommute.repository.ICityRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CityRepository implements ICityRepository {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String QUERY = "Select * from City";
    private final DataSource fDataSource;

    public CityRepository(final DataSource dataSource){
        this.fDataSource = Objects.requireNonNull(dataSource,"DataSource can't be null.");
    }

    @Override
    public List<ImmutableCity> getAllCities() {
        Jdbi jdbi = Jdbi.create(this.fDataSource);
        List<ImmutableCity> cities = new ArrayList<>();
        jdbi.useHandle(handle -> {
            final var result = handle.createQuery(QUERY)
                    .mapToMap()
                    .list();
            result.forEach(res -> cities.add(ImmutableCity.builder()
                    .id(res.get(ID).toString())
                    .name(res.get(NAME).toString())
                    .build() ));
        });
        return cities;
    }
}
