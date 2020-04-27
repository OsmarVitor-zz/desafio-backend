package com.project.citycontrol.api.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.citycontrol.api.exception.CityNotFoundException;
import com.project.citycontrol.api.models.Cities;
import com.project.citycontrol.api.repository.CitiesRepository;

@Service
public class CitiesServiceImpl implements CitiesService {

	@Autowired
	private CitiesRepository repository;

	public List<Cities> OpenCsv(String path) throws IOException {
		String line = "";
		List<Cities> CsvCities = new ArrayList<>();
		BufferedReader file = new BufferedReader(new FileReader(path));
		while ((line = file.readLine()) != null) {
			String[] data = line.split(",");
			Cities csv = new Cities();

			csv.setIbgeId(data[0]);
			csv.setUf(data[1]);
			csv.setName(data[2]);
			csv.setCapital(Boolean.valueOf(data[3]));
			csv.setLon(data[4]);
			csv.setLat(data[5]);
			csv.setNoAccents(data[6]);
			csv.setAlternativeNames(data[7]);
			csv.setMicroregion(data[8]);
			csv.setMesoregion(data[9]);
			CsvCities.add(csv);

		}
		file.close();
		return CsvCities;
	}

	@Override
	public List<Cities> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Cities> SaveCsv(String path) throws IOException {
		return repository.saveAll(OpenCsv(path));
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
