package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the wijnen database table.
 * 
 */
@Entity
@Table(name="wijnen")
public class Wijn implements Serializable, Comparable<Wijn> {
	
	private static final long serialVersionUID = 1L;

	// MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private byte beoordeling;
	private int inBestelling;
	private int jaar;
	private BigDecimal prijs;

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="soortid")
	private Soort soort;

	
	// CONSTRUCTORS
	protected Wijn() {}	
	
	public Wijn(byte beoordeling, int inBestelling, int jaar, BigDecimal prijs, Soort soort) {
		this.beoordeling = beoordeling;
		this.inBestelling = inBestelling;
		this.jaar = jaar;
		this.prijs = prijs;
		this.soort = soort;
	}

	
	// GETTERS & SETTERS
	public long getId() {
		return this.id;
	}
	public byte getBeoordeling() {
		return this.beoordeling;
	}
	public void setBeoordeling(byte beoordeling) {
		this.beoordeling = beoordeling;
	}
	public int getInBestelling() {
		return this.inBestelling;
	}
	public void setInBestelling(int inBestelling) {
		this.inBestelling = inBestelling;
	}
	public int getJaar() {
		return this.jaar;
	}
	public void setJaar(int jaar) {
		this.jaar = jaar;
	}
	public BigDecimal getPrijs() {
		return this.prijs;
	}
	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}
	public Soort getSoort() {
		return this.soort;
	}
	public void setSoort(Soort soort) {
		this.soort = soort;
	}

	
	// OVERRIDDEN OBJECT METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + jaar;
		result = prime * result + ((soort == null) ? 0 : soort.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Wijn))
			return false;
		Wijn other = (Wijn) obj;
		if (jaar != other.jaar)
			return false;
		if (soort == null) {
			if (other.soort != null)
				return false;
		} else if (!soort.equals(other.soort))
			return false;
		return true;
	}

	@Override
	public int compareTo(Wijn other) {
		return this.jaar - other.jaar;
	}
	
	
}