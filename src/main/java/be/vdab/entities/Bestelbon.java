package be.vdab.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import be.vdab.enums.Bestelwijze;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;


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
	private LocalDateTime besteld;
	private Bestelwijze bestelwijze;
	private String naam;
	@Embedded
	private Adres adres;
	@ElementCollection(fetch=FetchType.LAZY)
	@CollectionTable(name="bestelbonlijnen", joinColumns= @JoinColumn(name="bonid"))
	private Set<Bestelbonlijn> bestelbonlijnen;
	
	// CONSTRUCTORS
	protected Bestelbon() {}
	
	/**
	 * 
	 * @param bestelwijze
	 * @param naam
	 * @param adres
	 * @param bestelbonlijnen
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public Bestelbon(Bestelwijze bestelwijze, String naam, Adres adres, Set<Bestelbonlijn> bestelbonlijnen) 
			throws IllegalArgumentException, NullPointerException {
		this.besteld = LocalDateTime.now();
		setBestelwijze(bestelwijze);
		setNaam(naam);
		setAdres(adres);
		setBestelbonlijnen(bestelbonlijnen);
	}


	// GETTERS & SETTERS
	public long getId() {
		return this.id;
	}
	public LocalDateTime getBesteld() {
		return this.besteld;
	}
	public Bestelwijze getBestelwijze() {
		return this.bestelwijze;
	}
	public String getNaam() {
		return this.naam;
	}
	public Adres getAdres() {
		return adres;
	}
	public Set<Bestelbonlijn> getBestelbonlijnen() {
		return bestelbonlijnen;
	}
	
	public void setBestelwijze(Bestelwijze bestelwijze) {
		this.bestelwijze = bestelwijze;
	}
	public void setNaam(String naam) throws IllegalArgumentException {
		if (naam != null && !naam.equals("")) {
			this.naam = naam;
		}	
		else {
			throw new IllegalArgumentException("Ongeldige naam voor bestelbon. Mag niet null of leeg zijn");
		}
	}
	public void setAdres(Adres adres) throws NullPointerException {
		Objects.requireNonNull(adres, "Ongeldig adres opgegeven");
		this.adres = adres;
	}
	public void setBestelbonlijnen(Set<Bestelbonlijn> bestelbonlijnen) throws NullPointerException {
		Objects.requireNonNull(bestelbonlijnen, "Ongeldige bestelbonlijnen opgegeven");
		this.bestelbonlijnen = bestelbonlijnen;
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