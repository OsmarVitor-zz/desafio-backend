package com.project.citycontrol.api.service;

import java.io.IOException;
import java.util.List;

import com.project.citycontrol.api.models.Cities;
import org.springframework.web.multipart.MultipartFile;

public interface CitiesService {

	void saveCities(MultipartFile file) throws IOException;

	List<Cities> findAll();
	
	Cities findByIbgeId(String ibgeId);
	
	List<Cities> findCitiesByUf(String uf);
	
	Cities saveCity(Cities city);
	
	void deleteCity(String ibgeId);
}
