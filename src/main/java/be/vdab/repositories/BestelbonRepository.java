package be.vdab.repositories;

import javax.persistence.PersistenceException;

import be.vdab.entities.Bestelbon;

public class BestelbonRepository extends AbstractRepository {
	
	/**
	 * Probeert de gegeven bestelbon te persisten, en geeft de geflushte versie ervan terug.
	 * 
	 * @param bestelbon		
	 * @return				De bestelbon met gesynchroniseerde gegevens van de database na de flush, <br>
	 * 						of null wanneer er een PersistenceException werd geworpen.
	 */
	public Bestelbon tryCreate(Bestelbon bestelbon) {
		try {
			getEntityManager().persist(bestelbon);
			getEntityManager().flush();
			return bestelbon;
		}
		catch (PersistenceException ex) {
			return null;
		}
	}

}
