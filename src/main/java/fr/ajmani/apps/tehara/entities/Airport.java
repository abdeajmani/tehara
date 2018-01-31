package fr.ajmani.apps.tehara.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude= {"country","srcRoutes","dstRoutes"})
public class Airport {
	@Id
	private Integer airportId;
	
	// Name of airport.
	private String name;

	// City served by airport
	private String city;

	@Transient
	private String countryName;

	//IATA code
	private String iata;

	// ICAO code
	private String icao;

	//Decimal degrees
	private Double latitude; // y

	//Decimal degrees
	private Double longitude;// x

	//In feet
	private Integer altitude;

	//Hours offset from UTC
	private String timezone;

	/*
	 * Daylight savings time. One of E (Europe), A (US/Canada), S (South America),
	 * O (Australia), Z (New Zealand), N (None) or U (Unknown).
	 */
	private String dst;

	// Timezone 
	private String tz;
	
	
	@ManyToOne
	private Country country;
	
	@OneToMany(mappedBy="srcAirport")
	private List<Route> srcRoutes;
	
	@OneToMany(mappedBy="dstAirport")
	private List<Route> dstRoutes;
}
