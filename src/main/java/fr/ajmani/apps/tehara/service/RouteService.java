package fr.ajmani.apps.tehara.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import fr.ajmani.apps.tehara.csv.RoutesImporter;
import fr.ajmani.apps.tehara.entities.Airline;
import fr.ajmani.apps.tehara.entities.Airport;
import fr.ajmani.apps.tehara.entities.Route;
import fr.ajmani.apps.tehara.repository.AirlineRepository;
import fr.ajmani.apps.tehara.repository.AirportRepository;
import fr.ajmani.apps.tehara.repository.RouteRepository;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@DependsOn("airlineService")
public class RouteService {
	@Autowired
	RouteRepository routeRepository;

	@Autowired
	AirlineRepository airlineRepository;

	@Autowired
	AirportRepository airportRepository;

	private Airport getAirport(String airportId) {
		try {
			Airport airportFinder = new Airport();
			if (airportId.length() == 3) {
				// IATA
				airportFinder.setIata(airportId);
			} else if (airportId.length() == 4) {
				// ICAO
				airportFinder.setIcao(airportId);
			} else {
				log.error("Incorrect airportId: " + airportId);
				airportFinder = null;
			}
			if (airportFinder != null) {
				Example<Airport> finder = Example.of(airportFinder);
				Airport airport = airportRepository.findOne(finder);
				// if airline is null because not imported active = 'N'
				return airport;
			}
		} catch (IncorrectResultSizeDataAccessException e) {
			log.error("Multiple airport: " + airportId);

		}
		return null;
	}

	private Airline getAirline(String airlineId) {
		try {
			Airline airlineFinder = new Airline();

			if (airlineId.length() == 2) {
				// IATA
				airlineFinder.setIata(airlineId);
			} else if (airlineId.length() == 3) {
				// ICAO
				airlineFinder.setIcao(airlineId);
			} else {
				log.error("Incorrect airlineId: " + airlineId);
				airlineFinder = null;
			}
			if (airlineFinder != null) {
				Example<Airline> finder = Example.of(airlineFinder);
				Airline airline = airlineRepository.findOne(finder);
				// if airline is null because not imported active = 'N'
				return airline;
			}
		} catch (IncorrectResultSizeDataAccessException e) {
			log.error("Multiple airline: " + airlineId);

		}
		return null;
	}

	public void init() {
		if (routeRepository.count() == 0) {

			try {
				RoutesImporter importer = new RoutesImporter();
				List<Route> routesImported = importer.getDatas();

				List<Route> routes = new ArrayList<Route>();

				for (Route route : routesImported) {

					Airline airline = getAirline(route.getAirlineId());

					Airport srcAirport = getAirport(route.getSrc_ap());
					Airport dstAirport = getAirport(route.getDst_ap());

					if (airline != null && srcAirport != null && dstAirport != null) {
						route.setAirline(airline);
						route.setSrcAirport(srcAirport);
						route.setDstAirport(dstAirport);
						routes.add(route);
					} else {
						// airline is null -> defunct (N)
						// airport is null -> defunct (N)
						log.debug("route not added: " + route);
					}
				}

				routeRepository.save(routes);

				log.info(routes.size() + " routes created");

			} catch (IOException e) {
				log.error("Unable to retrieve routes: " + e.getMessage());
			}

		} else {
			log.info("countries already imported");
		}
	}

	public List<Route> getRoutes() {
		return (List<Route>) routeRepository.findAll();
	}
	
	public List<Route> getRoutes(Integer airportId) {
		
		Airport airport = airportRepository.findOne(airportId);

		Route routeFinder = new Route();
		routeFinder.setSrcAirport(airport);
		routeFinder.setDstAirport(airport);
		Example<Route> finder = Example.of(routeFinder);
		List<Route> routes = routeRepository.findAll(finder);
		log.info("routes size : " + routes.size());
		return routes;
	}

	public List<Route> getRoutesFrom(Integer airportId) {
		Airport airportFrom = airportRepository.findOne(airportId);

		Route routeFinder = new Route();
		routeFinder.setSrcAirport(airportFrom);
		Example<Route> finder = Example.of(routeFinder);

		return routeRepository.findAll(finder);
	}

	public List<Route> getRoutesTo(Integer airportId) {
		Airport airportTo = airportRepository.findOne(airportId);

		Route routeFinder = new Route();
		routeFinder.setDstAirport(airportTo);
		Example<Route> finder = Example.of(routeFinder);

		return routeRepository.findAll(finder);
	}
}
