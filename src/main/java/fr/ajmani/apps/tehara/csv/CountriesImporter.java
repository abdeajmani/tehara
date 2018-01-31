package fr.ajmani.apps.tehara.csv;

import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import fr.ajmani.apps.tehara.entities.Country;

public class CountriesImporter extends Importer<Country>{

	@Override
	protected String getDatafilename() {
		return "countries.dat";
	}

	@Override
	public Country convertToEntity(CSVRecord record) {
		Country country = new Country();
		String countryName = record.get(0);
		
		// fix country via http://www.geonames.org/countries/

		if(countryName.equals("Timor-Leste")) {
			countryName = "East Timor";
		}
		country.setName(countryName);
		country.setCode(record.get(1));
		country.setOaCode(record.get(2));
		country.setDst(record.get(3));
		return country;
	}
	
	@Override
	public List<Country> getDatas() throws IOException {
		return getDatas(Country.class);
	}

	

}
