package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Bestelbonlijn implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// MEMBER VARIABLES
	private long bonid;
	private long wijnid;
	private int aantal;
	
	
	// CONSTRUCTORS
	protected Bestelbonlijn() {}
	
	public Bestelbonlijn(int aantal) {
		this.aantal = aantal;
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
