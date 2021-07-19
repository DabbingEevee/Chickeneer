package com.existingeevee.chickeneer.genetics;

import java.util.Random;

import com.existingeevee.chickeneer.Utils;

public abstract class Allele<T> {

	private T value;
	private boolean dominant;
	private boolean blending;
	private float blendChance;


	public Allele(boolean dominant, T value) {
		this(dominant, value, false, 0f);
	}

	public Allele(boolean dominant, T value, boolean blending, float blendChance) {
		this.value = value;
		this.dominant = dominant;
		this.blending = blending;
		this.setBlendChance(blendChance);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T val) {
		this.value = val;
	}

	public final boolean isDominent() {
		return dominant;
	}

	public final boolean isBlendable() {
		return blending;
	}

	public abstract Allele<T> getBlendedAlleleEqual(String id, Allele<T> allele1, Allele<T> allele2, Random rand);
	public abstract Allele<T> getBlendedAlleleLessThan(String id, Allele<T> allele1, Allele<T> allele2, Random rand);
	public abstract Allele<T> getBlendedAlleleMoreathan(String id, Allele<T> allele1, Allele<T> allele2, Random rand);

	public final Trait blend(String id, Allele<T> allele2, Random rand) {
		Allele<T> allele1 = this;
		if (allele1.getValue() == allele2.getValue()) {
			return new Trait(id, allele1, allele2);
		}
		float blendChance = (allele1.getBlendChance() + allele2.getBlendChance()) / 2;
		if (rand.nextFloat() <= blendChance) {
			if (allele1.isDominent() == allele2.isDominent()) {
				return new Trait(id, getBlendedAlleleEqual(id, allele1, allele2, rand), rand.nextBoolean() ? allele1 : allele2, rand, true);
			}
			if (Utils.compareBoolean(allele1.isDominent(), allele2.isDominent())) {
				return new Trait(id, getBlendedAlleleMoreathan(id, allele1, allele2, rand), rand.nextBoolean() ? allele1 : allele2, rand, true);
			} else {
				return new Trait(id, getBlendedAlleleLessThan(id, allele1, allele2, rand), rand.nextBoolean() ? allele1 : allele2, rand, true);
			}
		}
		return new Trait(id, allele1, allele2);

	}

	public float getBlendChance() {
		return blendChance;
	}

	public void setBlendChance(float blendChance) {
		this.blendChance = Utils.clamp(blendChance, 0, 1);
	}

}
