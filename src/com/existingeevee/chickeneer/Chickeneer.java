package com.existingeevee.chickeneer;

import com.existingeevee.chickeneer.data.Color;
import com.existingeevee.chickeneer.genetics.DNA;
import com.existingeevee.chickeneer.genetics.Trait;
import com.existingeevee.chickeneer.genetics.allele.ColorAllele;

public class Chickeneer {

	public static void main(String[] args) {
		DNA dna1 = new DNA();
		dna1.getTraitMap().put("color", new Trait("color", new ColorAllele(false, new Color(0), true, 1), new ColorAllele(true, new Color(0xffffff), true, 1)));

		DNA dna2 = new DNA();
		dna2.getTraitMap().put("color", new Trait("color", new ColorAllele(false, new Color(0), true, 1), new ColorAllele(false, new Color(0xffffff), true, 1)));


		//7f7f7f
		//000000

		DNA result = new DNA(dna1, dna2);
		System.out.println(dna1);
		System.out.println(dna2);
		System.out.println(result);

	}

}
//ya yeet