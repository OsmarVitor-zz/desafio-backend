package com.project.citycontrol.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.citycontrol.api.models.City;

@Repository
public interface CitiesRepository extends JpaRepository<City, UUID>{
	Optional<City> findByIbgeId (String ibgeId);
	
	List<City> findCitiesByUf(String uf);
}
