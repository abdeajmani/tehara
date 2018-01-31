package fr.ajmani.apps.tehara.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.ajmani.apps.tehara.entities.Airport;
import fr.ajmani.apps.tehara.entities.Route;
import fr.ajmani.apps.tehara.service.AirportService;
import fr.ajmani.apps.tehara.service.RouteService;
import lombok.Data;


@Data
@ManagedBean
@Component
public class RouteView {
	
    @Autowired
    private RouteService routeService;
    
    @Autowired
    private AirportService airportService;
    
    private Integer airportId;
    
    public Airport getAirport() {
    	return airportService.getAirport(airportId);
    }
    
    public List<Route> getFrom(){
    	return routeService.getRoutesFrom(airportId);
    }
    
    public List<Route> getTo(){
    	return routeService.getRoutesTo(airportId);
    }
    
    public List<Route> getRoutes(){
    	return routeService.getRoutes(airportId);
    }

}
