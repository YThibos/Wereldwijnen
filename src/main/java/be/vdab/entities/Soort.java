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

/**
 * De immutable entity Soort die een wijnsoort voorstelt zoals ze wordt bijgehouden in de table soorten.
 * 
 */
@Entity
@Table(name="soorten")
public class Soort implements Serializable, Comparable<Soort> {
	
	private static final long serialVersionUID = 1L;

	// MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
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
	public long getId() {
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
	
	/**
	 * Vergelijkt twee landen op gelijkheid.
	 * 
	 * Twee landen zijn equal wanneer ze naar hetzelfde object verwijzen, of dezelfde naam én land hebben. 
	 * Namen zijn hetzelfde wanneer naam.compareTo(andereNaam) == 0. Land gelijkheid wordt gebaseerd op {@link be.vdab.Land.equals Land.equals}
	 */
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

	/**
	 * Vergelijkt twee soorten op lexicografische volgorde van hun naam. De teruggegeven waarde is dezelfde als this.naam.compareTo(andereNaam). 
	 * Wanneer beide soorten dezelfde naam hebben wordt de naam van het land vergeleken d.m.v. ${@link be.vdab.Land.comparteTo Land.compareTo}.
	 */
	@Override
	public int compareTo(Soort other) {
		if (this.naam.compareTo(other.naam) == 0) {
			return this.land.getNaam().compareTo(other.land.getNaam());
		}
		return this.naam.compareTo(other.naam);
	}
	
	/**
	 * Geeft de naam van de wijnsoort terug zonder extra opmaak.
	 */
	@Override
	public String toString() {
		return naam;
	}
	
}