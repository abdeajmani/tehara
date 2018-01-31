package fr.ajmani.apps.tehara.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.ajmani.apps.tehara.entities.Airline;
import fr.ajmani.apps.tehara.entities.Airport;
import fr.ajmani.apps.tehara.service.AirlineService;
import fr.ajmani.apps.tehara.service.AirportService;
import lombok.Data;


@Data
@ManagedBean
@Component
public class AirportView {
	
    @Autowired
    private AirportService airportService;
    
    
    @Autowired
    private AirlineService airlineService;
    
    private String countryName;
    
    public List<Airport> getAirports(){
    	return airportService.getAirportsByCountry(countryName);
    }
    
    public List<Airline> getAirlines(){
    	return airlineService.getAirlinesByCountry(countryName);
    }    
    

}
