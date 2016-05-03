package be.vdab.services;

import java.util.List;

import be.vdab.entities.Land;
import be.vdab.entities.Soort;
import be.vdab.repositories.SoortRepository;

public class SoortService extends AbstractService {

	private final SoortRepository soortRepository = new SoortRepository();
	
	public Soort read(long soortid) {
		return soortRepository.read(soortid);
	}
	
	public List<Soort> findByLand(Land land) {
		return soortRepository.findByLand(land);
	}
	
}
