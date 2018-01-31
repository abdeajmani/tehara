package fr.ajmani.apps.tehara.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.ajmani.apps.tehara.entities.Country;

public interface CountryRepository extends JpaRepository<Country, String> {
	@Override
	@Cacheable("country")
	<S extends Country> S findOne(Example<S> example);
}
