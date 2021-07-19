package com.existingeevee.chickeneer.genetics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

public class DNA {

	private UUID uuid = UUID.randomUUID();
	private Map<String,Trait> traitMap = new HashMap<String,Trait>();

	public DNA(Trait...traits) {
		for (Trait t : traits) {
			traitMap.put(t.getIdentifier(), t);
		}
	}

	public DNA(UUID uuid, Trait...traits) {
		this.uuid = uuid;
		for (Trait t : traits) {
			traitMap.put(t.getIdentifier(), t);
		}
	}

	public DNA(DNA dnaParent1, DNA dnaParent2) {
		this(dnaParent1, dnaParent2, new Random());
	}


	public DNA(DNA dnaParent1, DNA dnaParent2, Random rand) {
		List<Trait> traits = new ArrayList<Trait>();

		Map<String, Trait> parent1DNAMap = dnaParent1.getTraitMap();
		Map<String, Trait> parent2DNAMap = dnaParent2.getTraitMap();

		for (Entry<String, Trait> trait : parent1DNAMap.entrySet()) {
			if (parent2DNAMap.get(trait.getKey()) == null) {
				traits.add(trait.getValue());
				continue;
			}
		}
		for (Entry<String, Trait> trait : parent2DNAMap.entrySet()) {
			if (parent1DNAMap.get(trait.getKey()) == null) {
				traits.add(trait.getValue());
				continue;
			}

			int rng = (rand.nextInt(3) + 1);

			//aa, Aa, AA

			Allele a1 = trait.getValue().getAlleleA();
			Allele b1 = trait.getValue().getAlleleB();

			Allele a2 = parent1DNAMap.get(trait.getKey()).getAlleleA();
			Allele b2 = parent1DNAMap.get(trait.getKey()).getAlleleB();

			if (rng == 1) {
				if (a1.isBlendable() && a2.isBlendable()) {
					traits.add(a1.blend(trait.getKey(), a2, rand));
					continue;
				}
				traits.add(new Trait(trait.getKey(), a1, a2));
			} else if (rng == 2) {
				if (a2.isBlendable() && b1.isBlendable()) {
					traits.add(a2.blend(trait.getKey(), b1, rand));
					continue;
				}
				traits.add(new Trait(trait.getKey(), a2, b1));
			} else if (rng == 3) {
				if (a1.isBlendable() && b2.isBlendable()) {
					traits.add(a1.blend(trait.getKey(), b2, rand));
					continue;
				}
				traits.add(new Trait(trait.getKey(), a1, b2));
			} else if (rng == 4) {
				if (a1.isBlendable() && a2.isBlendable()) {
					traits.add(b1.blend(trait.getKey(), b2, rand));
					continue;
				}
				traits.add(new Trait(trait.getKey(), b1, b2));
			}
		}
		for (Trait t : traits) {
			traitMap.put(t.getIdentifier(), t);
		}
	}

	public UUID getUUID() {
		return uuid;
	}

	public Map<String,Trait> getTraitMap() {
		return this.traitMap;
	}

	@Override
	public String toString() {
		String dna = "[";
		for (Entry<String, Trait> t : traitMap.entrySet()) {
			int allele = 0;
			allele += (t.getValue().getAlleleA().isDominent() ? 1 : 0);
			allele += (t.getValue().getAlleleB().isDominent() ? 1 : 0);
			String alleleStr = "aa";
			if (allele == 1)
				alleleStr = "Aa";
			if (allele == 2)
				alleleStr = "AA";
			dna += ("(" + t.getKey() + " - " + alleleStr + " - " + t.getValue().getDominantAllele().getValue() + "), ");
		}

		return dna.substring(0, dna.length() -2) + "]";
	}

}
