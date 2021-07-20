package com.existingeevee.chickeneer.data;

import com.existingeevee.chickeneer.Utils;

public class Color {
	private int r;
	private int g;
	private int b;

	public Color(int r, int g, int b) {
		this.setR(r);
		this.setG(g);
		this.setB(b);
	}

	public Color(int color) {
		String hex = Integer.toHexString(color);
		if (!(hex.length() <= 6)) {
			throw new IllegalArgumentException();
		}
		hex = Utils.stringRepeat("0", Utils.clamp(6 - hex.length(), 0, 6)) + hex;
		int r = Utils.clamp(Integer.parseInt(hex.substring(0,2), 16), 0, 255);
		int g = Utils.clamp(Integer.parseInt(hex.substring(2,4), 16), 0, 255);
		int b = Utils.clamp(Integer.parseInt(hex.substring(4,6), 16), 0, 255);
		this.setR(r);
		this.setG(g);
		this.setB(b);
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = Utils.clamp(r, 0, 255);
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = Utils.clamp(g, 0, 255);
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = Utils.clamp(b, 0, 255);
	}

	@Override
	public String toString() {
		return "#" + Utils.stringRepeat("0", Utils.clamp(2 - Integer.toHexString(r).length(), 0, 2)) + Integer.toHexString(r) + Utils.stringRepeat("0", Utils.clamp(2 - Integer.toHexString(g).length(), 0, 2)) + Integer.toHexString(g) + Utils.stringRepeat("0", Utils.clamp(2 - Integer.toHexString(b).length(), 0, 2)) + Integer.toHexString(b);
	}

}
