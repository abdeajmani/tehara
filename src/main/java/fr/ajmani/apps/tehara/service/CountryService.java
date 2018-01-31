package fr.ajmani.apps.tehara.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ajmani.apps.tehara.csv.CountriesImporter;
import fr.ajmani.apps.tehara.entities.Country;
import fr.ajmani.apps.tehara.repository.CountryRepository;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class CountryService {
	@Autowired
	CountryRepository countryRepository;

	public void init() {
		if (countryRepository.count() == 0) {

			try {
				CountriesImporter importer = new CountriesImporter();
				List<Country> countriesImported = importer.getDatas();
				addMissingCountries(countriesImported);
				countryRepository.save(countriesImported);
				log.info(countriesImported.size() + " countries created");

			} catch (IOException e) {
				log.error("Unable to retrieve countries: " + e.getMessage());
			}

		} else {
			log.info("countries already imported");
		}
	}

	private void addMissingCountries(List<Country> countriesImported) {
		// add missing country via http://www.geonames.org/countries/
		
		Country palestine = new Country();
		palestine.setName("Palestine");
		palestine.setCode("PE");
		palestine.setOaCode("PS");
		palestine.setCode("U");
		countriesImported.add(palestine);

		Country myanmar = new Country();
		myanmar.setName("Myanmar");
		myanmar.setCode("BM");
		myanmar.setOaCode("MM");
		myanmar.setCode("U");
		countriesImported.add(myanmar);
		
		Country laos = new Country();
		laos.setName("Laos");
		laos.setCode("LA");
		laos.setOaCode("LA");
		laos.setCode("U");
		countriesImported.add(laos);
		
		Country macao = new Country();
		macao.setName("Macao");
		macao.setCode("MC");
		macao.setOaCode("MO");
		macao.setCode("U");
		countriesImported.add(macao);
		
		

	}

	public List<Country> getCountries() {
		return (List<Country>) countryRepository.findAll();
	}
}
