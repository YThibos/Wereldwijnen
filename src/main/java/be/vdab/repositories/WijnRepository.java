package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Soort;
import be.vdab.entities.Wijn;

public class WijnRepository extends AbstractRepository {

	public Wijn read(long id) {
		return getEntityManager().find(Wijn.class, id);
	}
	
	public List<Wijn> findBySoort(Soort soort) {
		return getEntityManager().createNamedQuery("Wijnen.findBySoort", Wijn.class)
				.setParameter("soort", soort)
				.getResultList();
	}
	
}
