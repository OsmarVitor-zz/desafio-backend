package com.project.citycontrol.api.controller;

import java.io.IOException;
import java.util.List;

import com.project.citycontrol.api.exception.FileBadRequestException;
import com.project.citycontrol.api.utils.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.citycontrol.api.models.Cities;
import com.project.citycontrol.api.service.CitiesService;

@RestController
@RequestMapping("api/cities")
public class CitiesController {

	@Autowired
	private CitiesService service;

	@PostMapping("file")
	public ResponseEntity saveFile(@RequestParam MultipartFile file) throws IOException {
		if(CsvUtil.isCsvFile(file)){
			service.saveCities(file);
			return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("api/cities/")
					.buildAndExpand().toUri()).build();
		}
		return ResponseEntity.badRequest().body(new FileBadRequestException("Extensão de arquivo inválida."));
	}

	@PostMapping
	public ResponseEntity<Cities> saveCity (@RequestBody Cities city){
		service.saveCity(city);
		return ResponseEntity
				.created(ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("api/cities/ibge-id/{ibgeId}")
						.buildAndExpand(city.getIbgeId()).toUri())
				.build();
	}

	@GetMapping
	public List<Cities> listCities() {
		return service.findAll();
	}

	@GetMapping("ibge-id/{ibgeId}")
	public ResponseEntity<Cities> findByIbgeId(@PathVariable(name = "ibgeId") String ibgeId) {
		return ResponseEntity.ok(service.findByIbgeId(ibgeId));
	}

	@GetMapping("uf/{uf}")
	public ResponseEntity<List<Cities>> findCitiesByUf(@PathVariable(name = "uf") String uf) {
		return ResponseEntity.ok(service.findCitiesByUf(uf));
	}
	
	@DeleteMapping("{ibgeId}")
	public ResponseEntity<Void> deleteCity(@PathVariable(name = "ibgeId") String ibgeId){
		service.deleteCity(ibgeId);
		return ResponseEntity.noContent().build();
	}
	
}
