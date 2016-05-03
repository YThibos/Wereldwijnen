package be.vdab.services;

import java.util.List;

import be.vdab.entities.Soort;
import be.vdab.entities.Wijn;
import be.vdab.repositories.WijnRepository;

public class WijnService extends AbstractService {
	
	private final WijnRepository wijnRepository = new WijnRepository();

	public Wijn read(long id) {
		return wijnRepository.read(id);
	}
	
	public List<Wijn> findBySoort(Soort soort) {
		return wijnRepository.findBySoort(soort);
	}
	
}
