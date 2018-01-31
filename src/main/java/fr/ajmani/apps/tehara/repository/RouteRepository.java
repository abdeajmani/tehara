package fr.ajmani.apps.tehara.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.ajmani.apps.tehara.entities.Route;

public interface RouteRepository extends JpaRepository<Route, Integer> {
	@Override
	@Cacheable("route")
	<S extends Route> S findOne(Example<S> example);
}
