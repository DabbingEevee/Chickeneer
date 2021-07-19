package com.existingeevee.chickeneer.genetics.allele;

import java.util.Random;

import com.existingeevee.chickeneer.genetics.Allele;

public class IntAllele extends Allele<Integer>{

	public IntAllele(boolean dominant, int value) {
		this(dominant, value, false, 0f);
	}

	public IntAllele(boolean dominant, Integer value, boolean blending, float blendChance) {
		super(dominant, value, blending, blendChance);
	}

	@Override
	public Allele<Integer> getBlendedAlleleEqual(String id, Allele<Integer> allele1, Allele<Integer> allele2, Random rand) {
		int value = Math.round(allele1.getValue() + allele2.getValue()) / 2;
		float blendChance = (allele1.getBlendChance() + allele2.getBlendChance()) / 2;
		return new IntAllele(allele1.isDominent() && allele2.isDominent(), value, true, blendChance / 2);
	}

	@Override
	public Allele<Integer> getBlendedAlleleLessThan(String id, Allele<Integer> allele1, Allele<Integer> allele2, Random rand) {
		float blendChance = (allele1.getBlendChance() + allele2.getBlendChance()) / 2;
		if (allele1.getValue() > allele2.getValue()) {
			int value = (Math.round(allele1.getValue() + allele2.getValue()) / 2) + (allele1.getValue() - allele2.getValue()) / 4;
			return new IntAllele(false, value, true, blendChance / 2);
		} else {
			int value = (Math.round(allele1.getValue() + allele2.getValue()) / 2) - (allele1.getValue() - allele2.getValue()) / 4;
			return new IntAllele(false, value, true, blendChance / 2);
		}
	}

	@Override
	public Allele<Integer> getBlendedAlleleMoreathan(String id, Allele<Integer> allele1, Allele<Integer> allele2, Random rand) {
		float blendChance = (allele1.getBlendChance() + allele2.getBlendChance()) / 2;
		if (allele1.getValue() > allele2.getValue()) {
			int value = (Math.round(allele1.getValue() + allele2.getValue()) / 2) - (allele1.getValue() - allele2.getValue()) / 4;
			return new IntAllele(false, value, true, blendChance / 2);
		} else {
			int value = (Math.round(allele1.getValue() + allele2.getValue()) / 2) + (allele1.getValue() - allele2.getValue()) / 4;
			return new IntAllele(false, value, true, blendChance / 2);
		}
	}

}
