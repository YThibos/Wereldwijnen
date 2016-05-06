package be.vdab.services;

import be.vdab.entities.Bestelbon;
import be.vdab.repositories.BestelbonRepository;

public class BestelbonService extends AbstractService {
	
	private final BestelbonRepository bestelbonRepository = new BestelbonRepository();

	public Bestelbon tryCreate(Bestelbon bestelbon) {
		try {
			beginTransaction();
			Bestelbon afterFlushBestelbon = bestelbonRepository.tryCreate(bestelbon);
			commit();
			return afterFlushBestelbon;
		} 
		catch (RuntimeException ex) {
			rollback();
			return null;
		}
	}
	
}
