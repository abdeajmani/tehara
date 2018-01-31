package fr.ajmani.apps.tehara.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Country {
	@Id
	private String name;
	
	//fips code see: http://www.geonames.org/countries/	
	private String code;
	
	//iso code
	private String oaCode;
	
	private String dst;
	
	@OneToMany(mappedBy = "country")
	private List<Airport> airports;

	@OneToMany(mappedBy = "country")	
	private List<Airline> airlines; 
}
