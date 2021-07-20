package com.existingeevee.chickeneer.data;

public class DoubleValue<A,B> {

	private A a;

	private B b;

	public DoubleValue(A a, B b){
		this.a = a;
		this.b = b;
	}

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "DVal(" + a + "," + b + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DoubleValue<?,?>) {
			return (this.getA().equals(((DoubleValue<?,?>) obj).getA()) && this.getB().equals(((DoubleValue<?,?>) obj).getB()));
		} else {
			return false;
		}
	}
}
