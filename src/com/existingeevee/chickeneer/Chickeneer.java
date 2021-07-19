package com.existingeevee.chickeneer;

import com.existingeevee.chickeneer.genetics.Allele;
import com.existingeevee.chickeneer.genetics.DNA;
import com.existingeevee.chickeneer.genetics.Trait;

public class Chickeneer {

	public static void main(String[] args) {
		DNA dna1 = new DNA();
		dna1.getTraitMap().put("red", new Trait("red", new Allele(true, 255, true, 1), new Allele(true, 255, true, 1)));//A400C4
		dna1.getTraitMap().put("green", new Trait("green", new Allele(true, 255, true, 1), new Allele(true, 255, true, 1)));//A400C4
		dna1.getTraitMap().put("blue", new Trait("blue", new Allele(true, 255, true, 1), new Allele(true, 255, true, 1)));//A400C4
		
		DNA dna2 = new DNA();
		dna2.getTraitMap().put("red", new Trait("red", new Allele(false, 114, true, 1), new Allele(false, 250, true, 1)));//A400C4
		dna2.getTraitMap().put("green", new Trait("green", new Allele(false, 30, true, 1), new Allele(false, 76, true, 1)));//A400C4
		dna2.getTraitMap().put("blue", new Trait("blue", new Allele(false, 146, true, 1), new Allele(false, 103, true, 1)));//A400C4

		DNA result = new DNA(dna1, dna2);
		System.out.println(dna1);
		System.out.println(dna2);
		System.out.println(result);

	}

}
//