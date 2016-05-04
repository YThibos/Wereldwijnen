package be.vdab.repositories;

import be.vdab.entities.Bestelbon;

public class BestelbonRepository extends AbstractRepository {
	
	/**
	 * 
	 * @param bestelbon
	 * @return
	 */
	public Bestelbon create(Bestelbon bestelbon) {
		getEntityManager().persist(bestelbon);
		getEntityManager().flush();
		return bestelbon;
	}

}
