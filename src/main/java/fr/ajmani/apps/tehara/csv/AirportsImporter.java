package fr.ajmani.apps.tehara.csv;

import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import fr.ajmani.apps.tehara.entities.Airport;

public class AirportsImporter extends Importer<Airport>{

	@Override
	protected String getDatafilename() {
		return "airports.dat";
	}

	@Override
	public List<Airport> getDatas() throws IOException {
		return getDatas(Airport.class);
	}

	@Override
	public Airport convertToEntity(CSVRecord record) {
		Airport airport = new Airport();
		
		airport.setAirportId(Integer.valueOf(record.get(0)));
		airport.setName(record.get(1));
		airport.setCity(record.get(2));
		airport.setCountryName(record.get(3));
		airport.setIata(record.get(4));
		airport.setIcao(record.get(5));
		airport.setLatitude(Double.valueOf(record.get(6)));
		airport.setLongitude(Double.valueOf(record.get(7)));
		airport.setAltitude(Integer.valueOf(record.get(8)));
		airport.setTimezone(record.get(9));
		airport.setDst(record.get(10));
		airport.setTz(record.get(11));
		
		return airport;
	}

}
