package fr.ajmani.apps.tehara.csv;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVRecord;

import fr.ajmani.apps.tehara.entities.Airline;

public class AirlineImporter extends Importer<Airline> {

	private final static HashMap<Integer, String> fixIataMap = new HashMap<Integer, String>() {

		private static final long serialVersionUID = -4585681925465535512L;
		{
			// [Airlineid]=IATA, name key not used is (Sky Express) non unique

			put(220, "ZN"); //incorrect iata
			put(4374, "GQ"); //incorrect iata
			
			put(3320,"DLH"); //use icao Lufthansa
			put(3321,"GEC"); //use icao Lufthansa Cargo
			
			put(4435,"SIA"); //use icao Singapore Airlines
			put(4464,"SQC"); //use icao Singapore Airlines Cargo		

		}
	};

	private final static HashMap<String, String> fixCountryMap = new HashMap<String, String>() {
		private static final long serialVersionUID = -6611501503708029119L;

		{
			put("", null);
			put("Republic of Korea", "South Korea");
			put("Ivory Coast", "Cote d'Ivoire");
			put("Democratic Republic of the Congo", "Congo (Kinshasa)");
			put("Democratic Republic of Congo", "Congo (Kinshasa)");
			put("Republic of the Congo", "Congo (Brazzaville)");
			put("Netherland", "Netherlands");
			put("Somali Republic", "Somalia");
			put("Syrian Arab Republic", "Syria");
			put("Lao Peoples Democratic Republic", "Laos");
			put("Democratic People's Republic of Korea", "North Korea");
			put("Russian Federation", "Russia");
			put("Russian Federation]]", "Russia");
			put("Hong Kong SAR of China", "Hong Kong");
			put("Canadian Territories", "Canada");
		}
	};

	Set<String> defunctAirlineList = new HashSet<String>() {
		private static final long serialVersionUID = -6554900530478022205L;

		{
			add("Formosa Airlines"); // VY
			add("Macair Airlines"); // CC
			add("Air Italy"); // I9 merged into Meridiana
			add("Japan Airlines Domestic"); // JL
			add("Maxair"); // 8M
			add("Trans Mediterranean Airlines"); // TL
			add("Maldivian Air Taxi"); // 8Q"
			add("City Connexion Airlines"); // G3
			add("Royal Nepal Airlines"); // duplicate Nepal Airlines

		}

	};

	@Override
	protected String getDatafilename() {
		return "airlines.dat";
	}

	@Override
	public List<Airline> getDatas() throws IOException {
		return getDatas(Airline.class);
	}

	private String getIata(Integer airlineId, String iataRecord) {
		String iataFixed = fixIataMap.get(airlineId);
		if (iataFixed != null) {
			return iataFixed;
		} else {
			return iataRecord;
		}
	}

	private String getCountry(String countryName) {
		String countryFixed = fixCountryMap.get(countryName);
		if (countryFixed != null) {
			return countryFixed;
		} else {
			return countryName;
		}
	}

	@Override
	public Airline convertToEntity(CSVRecord record) {
		String active = record.get(7);
		if (!active.equals("Y")) {
			return null;
		}

	String name = record.get(1);
		if (defunctAirlineList.contains(name)) {
			return null;
		}

		Airline airline = new Airline();

		Integer airlineId = Integer.valueOf(record.get(0));
		airline.setAirlineId(airlineId);
		airline.setName(name);
		airline.setAlias(record.get(2));
		airline.setIata(getIata(airlineId, record.get(3)));
		airline.setIcao(record.get(4));
		airline.setCallsign(record.get(5));

		airline.setCountryName(getCountry(record.get(6)));
		
		
		
		return airline;
	}

}
