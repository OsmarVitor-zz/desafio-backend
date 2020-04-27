package com.project.citycontrol.api;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CityControlApplication {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(CityControlApplication.class, args);
	}

}
