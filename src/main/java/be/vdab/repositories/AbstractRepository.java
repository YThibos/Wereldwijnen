package be.vdab.repositories;

import javax.persistence.EntityManager;

import be.vdab.filters.JPAFilter;

public class AbstractRepository {
	
	protected EntityManager getEntityManager() {
		return JPAFilter.getEntityManager();
	}

}
