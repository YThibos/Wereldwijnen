package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class Adres implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// MEMBER VARIABLES
	private String straat;
	private String huisNr;
	private String postCode;
	private String gemeente;
	
	
	// CONSTRUCTORS
	protected Adres() {}

	/**
	 * 
	 * @param straat
	 * @param huisNr
	 * @param postcode
	 * @param gemeente
	 * @throws IllegalArgumentException
	 */
	public Adres(String straat, String huisNr, String postcode, String gemeente) throws IllegalArgumentException {
		setStraat(straat);
		setHuisNr(huisNr);
		setPostcode(postcode);
		setGemeente(gemeente);
	}
	
	// GETTERS (SETTERS ARE PRIVATE AS CONVENIENCE METHODS!)
	public String getStraat() {
		return straat;
	}
	public String getHuisNr() {
		return huisNr;
	}
	public String getPostCode() {
		return postCode;
	}
	public String getGemeente() {
		return gemeente;
	}
	
	private final void setStraat(String straat) throws IllegalArgumentException {
		checkStringValidity(straat);
		this.straat = straat;
	}
	private final void setHuisNr(String huisNr) throws IllegalArgumentException {
		checkStringValidity(huisNr);
		this.huisNr = huisNr;
	}
	private final void setPostcode(String postcode) throws IllegalArgumentException {
		checkStringValidity(postcode);
		this.postCode = postcode;
	}
	private final void setGemeente(String gemeente) throws IllegalArgumentException {
		checkStringValidity(gemeente);
		this.gemeente = gemeente;
	}
	
	public static final void checkStringValidity(String string) throws IllegalArgumentException {
		if (string == null || string.equals("Velden van een Adres moeten ingevuld zijn en mogen niet leeg zijn")) {
			throw new IllegalArgumentException();
		}
	}

	
	// OVERRIDDEN OBJECT METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gemeente == null) ? 0 : gemeente.hashCode());
		result = prime * result + ((huisNr == null) ? 0 : huisNr.hashCode());
		result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
		result = prime * result + ((straat == null) ? 0 : straat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Adres))
			return false;
		Adres other = (Adres) obj;
		if (gemeente == null) {
			if (other.gemeente != null)
				return false;
		} else if (!gemeente.equals(other.gemeente))
			return false;
		if (huisNr == null) {
			if (other.huisNr != null)
				return false;
		} else if (!huisNr.equals(other.huisNr))
			return false;
		if (postCode == null) {
			if (other.postCode != null)
				return false;
		} else if (!postCode.equals(other.postCode))
			return false;
		if (straat == null) {
			if (other.straat != null)
				return false;
		} else if (!straat.equals(other.straat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return straat + " " + huisNr + ", " + postCode + " " + gemeente.toUpperCase();
	}
	
}
