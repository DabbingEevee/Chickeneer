package com.existingeevee.chickeneer;

import java.util.UUID;

import com.existingeevee.chickeneer.genetics.DNA;
import com.existingeevee.chickeneer.utils.Utils;

public class Chicken {

	private DNA chickenDNA;

	public Chicken(DNA dna) {
		this.chickenDNA = dna;
		Utils.saveDNAToFile(dna);
	}

	public UUID retrieveChickenUUID() {
		return this.chickenDNA.getUUID();
	}

	public Chicken breedChicken(Chicken chicken) {
		return new Chicken(new DNA(this.getChickenDNA(), chicken.getChickenDNA()));
	}

	public DNA getChickenDNA() {
		return chickenDNA;
	}

}
