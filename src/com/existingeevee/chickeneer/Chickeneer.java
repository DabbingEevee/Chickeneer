package com.existingeevee.chickeneer;

import com.existingeevee.chickeneer.genetics.Allele;
import com.existingeevee.chickeneer.genetics.DNA;
import com.existingeevee.chickeneer.genetics.Trait;

public class Chickeneer {

	public static void main(String[] args) {
		DNA dna1 = new DNA(new Trait("weedAmount", new Allele(true, 69, true, 0.5f), new Allele(false, 420, true, 0.5f)), new Trait("ppLength", new Allele(true, 0), new Allele(false, 1)));
		DNA dna2 = new DNA(new Trait("weedAmount", new Allele(true, 0, true, 0.5f), new Allele(true, 69, true, 0.5f)),    new Trait("ppLength", new Allele(true, 1), new Allele(false, 2)));

		DNA result = new DNA(dna1, dna2);

		System.out.println(result);

	}

}
