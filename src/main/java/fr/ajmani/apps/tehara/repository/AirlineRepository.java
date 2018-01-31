package fr.ajmani.apps.tehara.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.ajmani.apps.tehara.entities.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Integer>  {
	@Override
	@Cacheable("airline")
	<S extends Airline> S findOne(Example<S> example);
}
