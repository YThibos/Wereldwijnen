package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import be.vdab.util.InputValidator;

/**
 * De immutable entity Land die een land voorstelt zoals ze wordt bijgehouden in de table landen.
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
	
	public Land(String naam) throws IllegalArgumentException {
		this.naam = InputValidator.checkNotNullOrEmpty(naam);
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

	/**
	 * Vergelijkt twee landen op gelijkheid.
	 * 
	 * Twee landen zijn equal wanneer ze dezelfde naam hebben, of naar hetzelfde object verwijzen. Namen zijn hetzelfde wanneer naam.compareTo(andereNaam) == 0.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || ! (obj instanceof Land)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		return this.naam.compareTo(((Land)obj).naam) == 0;
	}

	/**
	 * Vergelijkt twee landen op lexicografische volgorde van hun naam. De teruggegeven waarde is dezelfde als this.naam.compareTo(andereNaam).
	 */
	@Override
	public int compareTo(Land other) {
		return this.naam.compareTo(other.naam);
	}
	
	/**
	 * Geeft de naam van het land terug zonder extra opmaak.
	 */
	@Override
	public String toString() {
		return this.naam;
	}
	
}