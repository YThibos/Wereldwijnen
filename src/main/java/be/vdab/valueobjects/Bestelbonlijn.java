package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Wijn;

@Embeddable
public class Bestelbonlijn implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// MEMBER VARIABLES
	private long bonid;
	private long wijnid;
	private int aantal;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	private Bestelbon bestelbon;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name="id")
	private Wijn wijn;
	
	
	// CONSTRUCTORS
	protected Bestelbonlijn() {}
	
	public Bestelbonlijn(int aantal, Bestelbon bestelbon, Wijn wijn) {
		this.aantal = aantal;
		this.bestelbon = bestelbon;
		this.wijn = wijn;
	}


	
	// GETTERS
	public long getBonid() {
		return bonid;
	}
	public long getWijnid() {
		return wijnid;
	}
	public int getAantal() {
		return aantal;
	}
	public Wijn getWijn() {
		return wijn;
	}
	public Bestelbon getBestelbon() {
		return bestelbon;
	}
	public BigDecimal getTotaalPrijs() {
		return this.wijn.getPrijs().multiply(BigDecimal.valueOf(aantal));
	}


	// OVERRIDDEN OBJECT METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aantal;
		result = prime * result + (int) (bonid ^ (bonid >>> 32));
		result = prime * result + (int) (wijnid ^ (wijnid >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Bestelbonlijn))
			return false;
		Bestelbonlijn other = (Bestelbonlijn) obj;
		if (aantal != other.aantal)
			return false;
		if (bonid != other.bonid)
			return false;
		if (wijnid != other.wijnid)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Bon: " + bonid + ", wijn: " + wijnid + ", aantal: " + aantal;
	}
	
	
	
}
