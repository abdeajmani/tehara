package fr.ajmani.apps.tehara;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import fr.ajmani.apps.tehara.service.AirlineService;
import fr.ajmani.apps.tehara.service.AirportService;
import fr.ajmani.apps.tehara.service.CountryService;
import fr.ajmani.apps.tehara.service.RouteService;
import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	AirportService airportService;
	
	@Autowired
	AirlineService airlineService;
	
	@Autowired
	RouteService routeService;
	
	
	
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
    	log.info("INITIALIZATION TEHARA APPLICATION STARTED ...");
    	countryService.init();
    	airportService.init();
    	airlineService.init();
    	routeService.init();
    	log.info("INITIALIZATION TEHARA APPLICATION STARTED");
    }
}
