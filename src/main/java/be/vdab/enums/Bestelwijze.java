package be.vdab.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Enumeratie van afhaalmogelijkheden die bij een bestelbon horen.
 * @author Thibos
 *
 */
public enum Bestelwijze {
	AFHALEN, LEVEREN, VLIEGTUIG;

	public static List<Bestelwijze> getValuesList() {
		return Arrays.asList(values());
	}
}
