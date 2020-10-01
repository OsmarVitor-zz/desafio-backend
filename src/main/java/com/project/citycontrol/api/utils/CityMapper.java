package com.project.citycontrol.api.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CityMapper implements RowMapper<City> {

    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        City city = new City();
        city.setUuid(UUID.fromString(rs.getString("uuid")));
        city.setAlternativeNames(rs.getString("alternative_names"));
        city.setCapital(rs.getBoolean("capital"));
        city.setIbgeId(rs.getString("ibge_id"));
        city.setLat(rs.getString("lat"));
        city.setLon(rs.getString("lon"));
        city.setMesoregion(rs.getString("mesoregion"));
        city.setMicroregion(rs.getString("microregion"));
        city.setName(rs.getString("name"));
        city.setNoAccents(rs.getString("no_accents"));
        city.setUf(rs.getString("uf"));
        return city;
    }
}
