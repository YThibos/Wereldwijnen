package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Land;

// TODO Javadoc
public class LandRepository extends AbstractRepository {

	public Land read(long id) {
		return getEntityManager().find(Land.class, id);
	}
	
	public List<Land> findAll() {
		return getEntityManager().createNamedQuery("Landen.findAll", Land.class)
				.getResultList();
	}
	
}
