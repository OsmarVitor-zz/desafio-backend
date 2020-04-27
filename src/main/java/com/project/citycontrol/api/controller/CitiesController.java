package com.project.citycontrol.api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.citycontrol.api.models.Cities;
import com.project.citycontrol.api.service.CitiesService;

@RestController
@RequestMapping("api/cities")
public class CitiesController {

	@Autowired
	private CitiesService service;

	@GetMapping("find/all")
	public List<Cities> listCities() {
		return service.findAll();
	}

	@PostMapping("save/csv")
	public void saveCsvOnDatabase() throws IOException {
		service.SaveCsv("src/main/resources/csv/cidades.csv");
	}

	@GetMapping("find/info/{ibgeId}")
	public ResponseEntity<Cities> findByIbgeId(@PathVariable(name = "ibgeId") String ibgeId) {
		return ResponseEntity.ok(service.findByIbgeId(ibgeId));
	}

	@GetMapping("find/uf/{uf}")
	public ResponseEntity<List<Cities>> findCitiesByUf(@PathVariable(name = "uf") String uf) {
		return ResponseEntity.ok(service.findCitiesByUf(uf));
	}
	
	@PostMapping("save/city")
	public ResponseEntity<Cities> saveCity (@RequestBody Cities city){
		service.saveCity(city);
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/cities/find/info/{ibgeId}")
                    .buildAndExpand(city.getIbgeId()).toUri())
                .build();
	} 
	
	@DeleteMapping("delete/{ibgeId}")
	public ResponseEntity<Void> deleteCity(@PathVariable(name = "ibgeId") String ibgeId){
		service.deleteCity(ibgeId);
		return ResponseEntity.noContent().build();
	}
	
}
