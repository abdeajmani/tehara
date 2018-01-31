package fr.ajmani.apps.tehara.csv;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import fr.ajmani.apps.tehara.entities.Country;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class TestImport {
	
	CountriesImporter importCountries = new CountriesImporter();
	
	@Test
	public void getCountries() throws IOException {
		
		log.info("start test...");

		//List<Country> countries = importCountries.getCountries();
		List<Country> countries = importCountries.getDatas();
		assertNotNull(countries);
		assertTrue(countries.size() > 0);
		log.info("SIZE = " + countries.size());

	}
}
