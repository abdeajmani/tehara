package fr.ajmani.apps.tehara.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.ajmani.apps.tehara.entities.Airport;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
	@Override
	@Cacheable("airport")
	<S extends Airport> S findOne(Example<S> example);
}
