package com.galaxy.model;

import java.util.ArrayList;
import java.util.List;
import com.galaxy.model.Civilization;
import org.springframework.stereotype.Component;


@Component
public class SolarSystem {
	private List<Civilization> civilizations = new ArrayList<>();

	public SolarSystem() {
		Civilization vulcanos = new Civilization(-1, 500, 0,"vulcanos");
		Civilization ferengis = new Civilization(-3, 2000, 0, "ferengis");
		Civilization betasoides = new Civilization(5, 1000, 0, "betasoides");

		civilizations.add(vulcanos);
		civilizations.add(ferengis);
		civilizations.add(betasoides);
	}

	public List<Civilization> getCivilizations() {
		return civilizations;
	}

	public void setCivilizations(List<Civilization> civilizations) {
		this.civilizations = civilizations;
	}
}
