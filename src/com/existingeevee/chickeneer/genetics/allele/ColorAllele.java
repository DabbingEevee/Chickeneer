package com.existingeevee.chickeneer.genetics.allele;

import java.util.Random;

import com.existingeevee.chickeneer.data.Color;
import com.existingeevee.chickeneer.genetics.Allele;
import com.existingeevee.chickeneer.misc.Utils;

public class ColorAllele extends Allele<Color>{

	public ColorAllele(boolean dominant, int color) {
		this(dominant, new Color(color), false, 0f);
	}

	public ColorAllele(boolean dominant, Color value) {
		this(dominant, value, false, 0f);
	}

	public ColorAllele(boolean dominant, Color value, boolean blending, float blendChance) {
		super(dominant, value, blending, blendChance);
	}

	@Override
	public Allele<Color> getBlendedAllele(String id, Allele<Color> allele1, Allele<Color> allele2, Random rand) {
		float blendChance = (allele1.getBlendChance() + allele2.getBlendChance()) / 2;

		if (allele1.isDominent() == allele2.isDominent()) {
			int valueR = Math.round(allele1.getValue().getR() + allele2.getValue().getR()) / 2;
			int valueG = Math.round(allele1.getValue().getG() + allele2.getValue().getG()) / 2;
			int valueB = Math.round(allele1.getValue().getB() + allele2.getValue().getB()) / 2;

			return new ColorAllele(allele1.isDominent() && allele2.isDominent(), new Color(valueR,valueG,valueB), true, blendChance / 2);
		}
		if (Utils.compareBoolean(allele1.isDominent(), allele2.isDominent())) {
			int valueR = 0;
			int valueG = 0;
			int valueB = 0;
			if (allele1.getValue().getR() > allele2.getValue().getR()) {
				valueR = (Math.round(allele1.getValue().getR() + allele2.getValue().getR()) / 2) + (allele1.getValue().getR() - allele2.getValue().getR()) / 4;
			} else {
				valueR = (Math.round(allele1.getValue().getR() + allele2.getValue().getR()) / 2) - (allele1.getValue().getR() - allele2.getValue().getR()) / 4;
			}
			if (allele1.getValue().getG() > allele2.getValue().getG()) {
				valueG = (Math.round(allele1.getValue().getG() + allele2.getValue().getG()) / 2) + (allele1.getValue().getG() - allele2.getValue().getG()) / 4;
			} else {
				valueG = (Math.round(allele1.getValue().getG() + allele2.getValue().getG()) / 2) - (allele1.getValue().getG() - allele2.getValue().getG()) / 4;
			}
			if (allele1.getValue().getB() > allele2.getValue().getB()) {
				valueB = (Math.round(allele1.getValue().getB() + allele2.getValue().getB()) / 2) + (allele1.getValue().getB() - allele2.getValue().getB()) / 4;
			} else {
				valueB = (Math.round(allele1.getValue().getB() + allele2.getValue().getB()) / 2) - (allele1.getValue().getB() - allele2.getValue().getB()) / 4;
			}
			return new ColorAllele(false, new Color(valueR, valueG, valueB), true, blendChance / 2);

		} else {
			int valueR = 0;
			int valueG = 0;
			int valueB = 0;
			if (allele1.getValue().getR() > allele2.getValue().getR()) {
				valueR = (Math.round(allele1.getValue().getR() + allele2.getValue().getR()) / 2) - (allele1.getValue().getR() - allele2.getValue().getR()) / 4;
			} else {
				valueR = (Math.round(allele1.getValue().getR() + allele2.getValue().getR()) / 2) + (allele1.getValue().getR() - allele2.getValue().getR()) / 4;
			}
			if (allele1.getValue().getG() > allele2.getValue().getG()) {
				valueG = (Math.round(allele1.getValue().getG() + allele2.getValue().getG()) / 2) - (allele1.getValue().getG() - allele2.getValue().getG()) / 4;
			} else {
				valueG = (Math.round(allele1.getValue().getG() + allele2.getValue().getG()) / 2) + (allele1.getValue().getG() - allele2.getValue().getG()) / 4;
			}
			if (allele1.getValue().getB() > allele2.getValue().getB()) {
				valueB = (Math.round(allele1.getValue().getB() + allele2.getValue().getB()) / 2) - (allele1.getValue().getB() - allele2.getValue().getB()) / 4;
			} else {
				valueB = (Math.round(allele1.getValue().getB() + allele2.getValue().getB()) / 2) + (allele1.getValue().getB() - allele2.getValue().getB()) / 4;
			}
			return new ColorAllele(false, new Color(valueR, valueG, valueB), true, blendChance / 2);
		}

	}
}
