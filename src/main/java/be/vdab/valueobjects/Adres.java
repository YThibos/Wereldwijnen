package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

import be.vdab.util.InputValidator;

/**
 * Immutable value object dat een adres voorstelt. Elk adres dient steeds compleet ingevuld te worden.
 * Een geldig veld is een niet-null, niet-lege string.
 * 
 * @author Yannick Thibos
 *
 */
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
	 * Maakt een adres object aan en controleert meegegeven strings op correctheid. Elk adres dient steeds compleet ingevuld te worden.
	 * Een geldig veld is een niet-null, niet-lege string.
	 * 
	 * @throws IllegalArgumentException		Wordt geworpen wanneer minstens één parameter niet aan de voorwaarde voldoet.
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
		this.straat = InputValidator.checkNotNullOrEmpty(straat);
	}
	private final void setHuisNr(String huisNr) throws IllegalArgumentException {
		this.huisNr = InputValidator.checkNotNullOrEmpty(huisNr);
	}
	private final void setPostcode(String postcode) throws IllegalArgumentException {
		this.postCode = InputValidator.checkNotNullOrEmpty(postcode);
	}
	private final void setGemeente(String gemeente) throws IllegalArgumentException {
		this.gemeente = InputValidator.checkNotNullOrEmpty(gemeente);
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

	/**
	 * Een adres is equal aan een ander adres wanneer ze naar hetzelfde object verwijzen, 
	 * of wanneer álle velden exact dezelfde zijn (volgens String.equals).
	 */
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

	/**
	 * Returnt return straat + " " + huisNr + ", " + postCode + " " + gemeente.toUpperCase()
	 */
	@Override
	public String toString() {
		return straat + " " + huisNr + ", " + postCode + " " + gemeente.toUpperCase();
	}
	
}
