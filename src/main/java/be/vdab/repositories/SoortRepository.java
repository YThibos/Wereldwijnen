package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Land;
import be.vdab.entities.Soort;

public class SoortRepository extends AbstractRepository {

	public Soort read(long id) {
		return getEntityManager().find(Soort.class, id);
	}
	
	public List<Soort> findByLand(Land land) {
		return getEntityManager().createNamedQuery("Soorten.findByLand", Soort.class)
				.setParameter("land", land)
				.getResultList();
	}
	
}
