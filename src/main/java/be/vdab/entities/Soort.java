package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// TODO Javadoc (immutable class)
/**
 * The persistent class for the soorten database table.
 * 
 */
@Entity
@Table(name="soorten")
public class Soort implements Serializable, Comparable<Soort> {
	
	private static final long serialVersionUID = 1L;

	// MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String naam;

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="landid")
	private Land land;

	
	// CONSTRUCTORS
	protected Soort() {}
	
	public Soort(String naam, Land land) {
		this.naam = naam;
	}

	
	// GETTERS
	public int getId() {
		return this.id;
	}
	public String getNaam() {
		return this.naam;
	}
	public Land getLand() {
		return this.land;
	}

	
	// OVERRIDDEN OBJECT METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((land == null) ? 0 : land.hashCode());
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Soort))
			return false;
		Soort other = (Soort) obj;
		if (land == null) {
			if (other.land != null)
				return false;
		} else if (!land.equals(other.land))
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

	@Override
	public int compareTo(Soort other) {
		if (this.equals(other)) {
			return 0;
		}
		if (this.naam.compareTo(other.naam) == 0) {
			return this.land.getNaam().compareTo(other.land.getNaam());
		}
		return this.naam.compareTo(other.naam);
	}
	
	@Override
	public String toString() {
		return naam;
	}
	
}