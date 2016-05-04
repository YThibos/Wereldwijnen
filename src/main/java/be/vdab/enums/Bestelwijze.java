package be.vdab.enums;

import java.util.Arrays;
import java.util.List;

public enum Bestelwijze {
	AFHALEN, LEVEREN, VLIEGTUIG;

	public static List<Bestelwijze> getValuesList() {
		return Arrays.asList(values());
	}
}
