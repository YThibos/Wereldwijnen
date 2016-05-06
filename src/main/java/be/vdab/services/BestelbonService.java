package be.vdab.services;

import be.vdab.entities.Bestelbon;
import be.vdab.repositories.BestelbonRepository;

public class BestelbonService extends AbstractService {
	
	private final BestelbonRepository bestelbonRepository = new BestelbonRepository();

	/**
	 * Probeert de gegeven bestelbon te persisten, en geeft de geflushte versie ervan terug.<br>
	 * Als dit niet is gelukt (door een Exception) wordt een rollback gedaan.
	 * 
	 * @param bestelbon		
	 * @return				De bestelbon met gesynchroniseerde gegevens van de database na de flush, <br>
	 * 						of null wanneer er een PersistenceException werd geworpen.
	 */
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
