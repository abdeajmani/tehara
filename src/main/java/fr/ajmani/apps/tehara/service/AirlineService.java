package fr.ajmani.apps.tehara.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import fr.ajmani.apps.tehara.csv.AirlineImporter;
import fr.ajmani.apps.tehara.entities.Airline;
import fr.ajmani.apps.tehara.entities.Country;
import fr.ajmani.apps.tehara.repository.AirlineRepository;
import fr.ajmani.apps.tehara.repository.CountryRepository;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@DependsOn("countryService")
public class AirlineService {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	AirlineRepository airlineRepository;

	
	public void init() {
		if (airlineRepository.count() == 0) {

			try {
				AirlineImporter importer = new AirlineImporter();
				List<Airline> airlinesImported = importer.getDatas();
				for (Airline airline : airlinesImported) {
					String countryName = airline.getCountryName();
					if (! StringUtils.isEmpty(countryName)) {
						Country country = countryRepository.findOne(countryName);
						if (country == null) {
							log.warn("Unable to retrieve country: " + airline);
						}
						airline.setCountry(country);
					}
				}
				airlineRepository.save(airlinesImported);
				log.info(airlinesImported.size() + " airlines created");

			} catch (IOException e) {
				log.error("Unable to retrieve airlines: " + e.getMessage());
			}

		} else {
			log.info("airlines already imported");
		}
	}

	public List<Airline> getAirlines() {
		return (List<Airline>) airlineRepository.findAll();
	}

	public List<Airline> getAirlinesByCountry(String countryName) {
		Country country = countryRepository.findOne(countryName);
		return country.getAirlines();
	}

}
