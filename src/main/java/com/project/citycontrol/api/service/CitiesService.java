package com.project.citycontrol.api.service;

import java.io.IOException;
import java.util.List;

import com.project.citycontrol.api.models.Cities;

public interface CitiesService {
	List<Cities> findAll();
	
	List<Cities> SaveCsv(String path)throws IOException;
	
	Cities findByIbgeId(String ibgeId);
	
	List<Cities> findCitiesByUf(String uf);
	
	Cities saveCity(Cities city);
	
	void deleteCity(String ibgeId);
}
