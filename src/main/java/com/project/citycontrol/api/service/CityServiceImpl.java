package com.project.citycontrol.api.service;

import java.io.IOException;
import java.util.List;

import com.project.citycontrol.api.utils.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.citycontrol.api.exception.CityNotFoundException;
import com.project.citycontrol.api.models.Cities;
import com.project.citycontrol.api.repository.CitiesRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CitiesServiceImpl implements CitiesService {

	@Autowired
	private CitiesRepository repository;

	@Override
	public void saveCities(MultipartFile file) throws IOException {
		List<Cities> cities = CsvUtil.readCsv(file.getInputStream());
		repository.saveAll(cities);
	}

	@Override
	public List<Cities> findAll() {
		return repository.findAll();
	}

	@Override
	public Cities findByIbgeId(String ibgeId) {
		Cities city = repository.findByIbgeId(ibgeId).orElseThrow(
				() -> new CityNotFoundException("Cidade com o código do IBGE [" + ibgeId
						+ "] não encontrado no sistema."));
		return city;
	}
	
	@Override
	public List<Cities> findCitiesByUf(String uf){
		return repository.findCitiesByUf(uf);
	}

	@Override
	public Cities saveCity(Cities city) {
		return repository.save(city);
	}

	@Override
	public void deleteCity(String ibgeId) {
		Cities city = repository.findByIbgeId(ibgeId).orElseThrow(
				() -> new CityNotFoundException("Cidade com o código do IBGE [" 
						+ ibgeId+ "] não econtrado no sistema."));
		repository.delete(city);
	}

}
