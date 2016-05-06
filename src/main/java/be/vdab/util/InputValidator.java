package be.vdab.util;

public final class InputValidator {
	
	private InputValidator() {};
	
	/**
	 * Valideert een String op niet-null en niet leeg te zijn. De teruggegeven String is dezelfde als de parameter.  
	 * 
	 * @param string						De te controleren String.
	 * @return								De String die werd meegegeven als parameter.
	 * @throws IllegalArgumentException		Wordt geworpen wanneer de parameter een null-reference of een lege ("") String was.
	 */
	public static String checkNotNullOrEmpty(String string) throws IllegalArgumentException {
		if (string == null || string.equals("")) {
			throw new IllegalArgumentException("Opgegeven string mag niet null of leeg zijn.");
		}
		return string;
	}
	
}
