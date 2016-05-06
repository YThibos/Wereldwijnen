package be.vdab.repositories;

import javax.persistence.PersistenceException;

import be.vdab.entities.Bestelbon;

public class BestelbonRepository extends AbstractRepository {
	
	/**
	 * 
	 * @param bestelbon
	 * @return
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
