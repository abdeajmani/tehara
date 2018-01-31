package fr.ajmani.apps.tehara.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Airline {
	@Id
	private Integer airlineId;
	
	private String name;
	
	private String alias;
	
	private String iata;
	
	private String icao;
	
	private String callsign;
	
	@Transient
	private String countryName;
	
	@ManyToOne
	private Country country;
	
	@OneToMany(mappedBy="airline")
	private List<Route> routes;
	
}
