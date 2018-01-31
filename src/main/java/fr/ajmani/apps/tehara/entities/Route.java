package fr.ajmani.apps.tehara.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Route {
	@Id
	@GeneratedValue
	private Integer routeId;

	// 2-letter (IATA) or 3-letter (ICAO) code of the airline
	@Transient
	private String airlineId;

	@Transient
	// Unique OpenFlights identifier for airline
	private Integer alid;

	@Transient
	// 3-letter (IATA) or 4-letter (ICAO) code of the source airport.
	private String src_ap;

	@Transient
	// Unique identifier for source airport (see Airport)
	private Integer src_apid;

	@Transient
	// 3-letter (IATA) or 4-letter (ICAO) code of the destination airport.
	private String dst_ap;

	@Transient
	// Unique identifier for destination airport (see Airport)
	private Integer dst_apid;

	/*
	 * "Y" if this flight is a codeshare (that is, not operated by Airline, but
	 * another carrier), empty otherwise
	 */
	private String codeshare;

	// Number of stops on this flight ("0" for direct)
	private String stops;

	/*
	 * 3-letter codes for plane type(s) generally used on this flight, separated by
	 * spaces
	 * 
	 * The data is ISO 8859-1 (Latin-1) encoded. The special value \N is used for
	 * "NULL" to indicate that no value is available, and is understood
	 * automatically by MySQL if imported.
	 */
	private String equipment;
	
	@ManyToOne
	private Airline airline;
	
	@ManyToOne
	private Airport srcAirport;
	
	@ManyToOne
	private Airport dstAirport;
	
	
	 
}
