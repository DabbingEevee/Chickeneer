package com.existingeevee.chickeneer.genetics;

import java.util.Random;

import com.existingeevee.chickeneer.Utils;
import com.existingeevee.chickeneer.data.DoubleValue;

public class Trait {

	private String identifier;
	private DoubleValue<Allele<?>,Allele<?>> allelePair;
	private boolean isAlleleADominant;

	public Trait(String identifier, Allele<?> alleleA, Allele<?> alleleB) {
		this(identifier, alleleA, alleleB, new Random());
	}

	public Trait(String identifier, Allele<?> alleleA, Allele<?> alleleB, Random rand) {
		this.identifier = identifier;
		this.allelePair = new DoubleValue<Allele<?>,Allele<?>>(alleleA, alleleB);
		this.isAlleleADominant = (alleleA.isDominent() == alleleB.isDominent() ? rand.nextBoolean() : Utils.compareBoolean(alleleA.isDominent(), alleleB.isDominent()));
	}

	public Trait(String identifier, Allele<?> alleleA, Allele<?> alleleB, Random rand, boolean isAlleleADominant) {
		this.identifier = identifier;
		this.allelePair = new DoubleValue<Allele<?>,Allele<?>>(alleleA, alleleB);
		this.isAlleleADominant = isAlleleADominant;
	}
	public final Allele<?> getDominantAllele() {
		return (this.isAlleleADominant ? allelePair.getA() : allelePair.getB());
	}

	public final String getIdentifier() {
		return identifier;
	}

	public final DoubleValue<Allele<?>,Allele<?>> getAllelePair() {
		return allelePair;
	}

	public final Allele<?> getAlleleA() {
		return allelePair.getA();
	}

	public final Allele<?> getAlleleB() {
		return allelePair.getB();
	}

	public Allele<?> getResessiveAllele() {
		return (this.isAlleleADominant ? allelePair.getB() : allelePair.getA());
	}

}
