package fr.ajmani.apps.tehara.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import fr.ajmani.apps.tehara.csv.AirportsImporter;
import fr.ajmani.apps.tehara.entities.Airport;
import fr.ajmani.apps.tehara.entities.Country;
import fr.ajmani.apps.tehara.repository.AirportRepository;
import fr.ajmani.apps.tehara.repository.CountryRepository;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@DependsOn("countryService")
public class AirportService {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	AirportRepository airportRepository;

	public void init() {
		if (airportRepository.count() == 0) {

			try {
				AirportsImporter importer = new AirportsImporter();
				List<Airport> airportsImported = importer.getDatas();
				for (Airport airport : airportsImported) {
					String airportName = airport.getName();
					if (airportName.contains("\"")) {
						airport.setName(airportName.replaceAll("\"", "'"));
						log.warn("airport name replaced : " + airport.getName());
					}
					Country country = countryRepository.findOne(airport.getCountryName());
					airport.setCountry(country);
				}
				airportRepository.save(airportsImported);
				log.info(airportsImported.size() + " airports created");

			} catch (IOException e) {
				log.error("Unable to retrieve airports: " + e.getMessage());
			}

		} else {
			log.info("airports already imported");
		}
	}

	public List<Airport> getAirports() {
		return (List<Airport>) airportRepository.findAll();
	}

	public List<Airport> getAirportsByCountry(String countryName) {
		Country country = countryRepository.findOne(countryName);
		return country.getAirports();
	}

	public Airport getAirport(Integer airportId) {
		return airportRepository.findOne(airportId);
	}

}
