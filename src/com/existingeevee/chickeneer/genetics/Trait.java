package com.existingeevee.chickeneer.genetics;

public abstract class Trait<V> {

	private String identifier;
	private int dominance;
	private V value;
	
	public Trait(String identifier, int dominance, V value) {
		this.identifier = identifier;
		this.dominance = dominance; 
		this.value = value;
	}

	public V getValue() {
		return value;
	}

	public int getDominance() {
		return dominance;
	}

	public String getIdentifier() {
		return identifier;
	}

}
