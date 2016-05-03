package be.vdab.entities;

// TODO Javadoc
import java.io.Serializable;
import javax.persistence.*;

import be.vdab.enums.Bestelwijze;
import be.vdab.valueobjects.Adres;

import java.util.Date;


/**
 * The persistent class for the bestelbonnen database table.
 * 
 */
@Entity
@Table(name="bestelbonnen")
public class Bestelbon implements Serializable, Comparable<Bestelbon> {
	
	private static final long serialVersionUID = 1L;

	// MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	// TODO LocalDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date besteld;
	private Bestelwijze bestelwijze;
	private String naam;
	@Embedded
	private Adres adres;

	
	// CONSTRUCTORS
	protected Bestelbon() {}
	

	// GETTERS & SETTERS
	public long getId() {
		return this.id;
	}
	public Date getBesteld() {
		return this.besteld;
	}
	public Bestelwijze getBestelwijze() {
		return this.bestelwijze;
	}
	public void setBestelwijze(Bestelwijze bestelwijze) {
		this.bestelwijze = bestelwijze;
	}
	public String getNaam() {
		return this.naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public Adres getAdres() {
		return adres;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}


	// OVERRIDDEN OBJECT METHODS
	@Override
	public int hashCode() {
		// besteld is eigenlijk niet uniek genoeg, timestamp zou beter zijn
		final int prime = 31;
		int result = 1;
		result = prime * result + ((besteld == null) ? 0 : besteld.hashCode());
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// idem comment als bij hashCode
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Bestelbon))
			return false;
		Bestelbon other = (Bestelbon) obj;
		if (besteld == null) {
			if (other.besteld != null)
				return false;
		} else if (!besteld.equals(other.besteld))
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}


	@Override
	public int compareTo(Bestelbon other) {
		if (this.equals(other)) {
			return 0;
		}
		if (this.naam.compareTo(other.naam) == 0) {
			return this.besteld.compareTo(other.besteld);
		}
		return this.naam.compareTo(other.naam);
	}
	
}