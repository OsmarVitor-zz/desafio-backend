package com.project.citycontrol.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.citycontrol.api.models.Cities;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, UUID>{
	Optional<Cities> findByIbgeId (String ibgeId);
	
	List<Cities> findCitiesByUf(String uf);
}
