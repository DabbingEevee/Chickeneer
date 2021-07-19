package com.existingeevee.chickeneer.genetics;

import com.existingeevee.chickeneer.Utils;

public class Allele {

	private int value;
	private boolean dominant;
	private boolean blending;
	private float blendChance;


	public Allele(boolean dominant, int value) {
		this(dominant, value, false, 0f);
	}

	public Allele(boolean dominant, int value, boolean blending, float blendChance) {
		this.value = value;
		this.dominant = dominant;
		this.blending = blending;
		this.setBlendChance(blendChance);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int val) {
		this.value = val;
	}

	public boolean isDomanent() {
		return dominant;
	}

	public boolean isBlendable() {
		return blending;
	}

	public float getBlendChance() {
		return blendChance;
	}

	public void setBlendChance(float blendChance) {
		this.blendChance = Utils.clamp(blendChance, 0, 1);
	}

}
