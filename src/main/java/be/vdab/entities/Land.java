package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO Javadoc (immutable class)
/**
 * The persistent class for the landen database table.
 * 
 */
@Entity
@Table(name="landen")
public class Land implements Serializable, Comparable<Land> {

	private static final long serialVersionUID = 1L;

	// MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String naam;


	// CONSTRUCTORS
	protected Land() {}
	
	public Land(String naam) {
		this.naam = naam;
	}

	
	// GETTERS
	public long getId() {
		return this.id;
	}
	public String getNaam() {
		return this.naam;
	}

	
	// OVERRIDDEN OBJECT METHODS
	@Override
	public int hashCode() {
		return naam.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || ! (obj instanceof Land)) {
			return false;
		}
		return this.naam == ((Land)obj).naam;
	}

	@Override
	public int compareTo(Land other) {
		return this.naam.compareTo(other.naam);
	}
	
	@Override
	public String toString() {
		return this.naam;
	}
	
}