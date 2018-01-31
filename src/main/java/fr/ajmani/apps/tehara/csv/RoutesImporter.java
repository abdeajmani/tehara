package fr.ajmani.apps.tehara.csv;

import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import fr.ajmani.apps.tehara.entities.Route;

public class RoutesImporter extends Importer<Route>{

	@Override
	protected String getDatafilename() {
		return "routes.dat";
	}	
	
	@Override
	public Route convertToEntity(CSVRecord record) {
		Route route = new Route();
		route.setAirlineId(record.get(0));
		route.setAlid(parseInt(record.get(1)));		
		route.setSrc_ap(record.get(2));
		route.setSrc_apid(parseInt(record.get(3)));
		route.setDst_ap(record.get(4));		
		route.setDst_apid(parseInt(record.get(5)));		
		route.setCodeshare(record.get(6));		
		route.setStops(record.get(7));
		route.setEquipment(record.get(8));
		
		return route;
	}
	
	@Override
	public List<Route> getDatas() throws IOException {
		return getDatas(Route.class);
	}

	

}
