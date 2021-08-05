package com.existingeevee.chickeneer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.existingeevee.chickeneer.genetics.Allele;
import com.existingeevee.chickeneer.genetics.DNA;
import com.existingeevee.chickeneer.genetics.Trait;
import com.existingeevee.chickeneer.utils.Utils;

public class Chickeneer {

	public static List<DNA> dnaList = new ArrayList<DNA>();

	public static void main(String[] args) {

		File folder = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "dnapool");

		for (File f : folder.listFiles()) {
			try {
				UUID.fromString(f.getName());
				if (!f.isDirectory()) {
					continue;
				} else {
					DNA dna = new DNA(UUID.fromString(f.getName()));
					for (File t : f.listFiles()) {
						try {
							if (!t.isDirectory()) {
								continue;
							} else {
								Allele<?> a = Utils.deserializeAllele(Utils.fileread("dnapool/" + f.getName() + "/" + t.getName() + "/a.json"));
								Allele<?> b = Utils.deserializeAllele(Utils.fileread("dnapool/" + f.getName() + "/" + t.getName() + "/b.json"));
								Map<String,String> map = Utils.readJsonFile("dnapool/" + f.getName() + "/" + t.getName() + "/traitdata");
								Trait tr = new Trait(t.getName(), a, b, new Random(), map.get("dominant").equalsIgnoreCase("a") ? true : false);
								dna.getTraitMap().put(tr.getIdentifier(), tr);
							}
						} catch(Throwable e) {
							e.printStackTrace();
							continue;
						}
					}
					dnaList.add(dna);
				}
			} catch(Throwable e) {
				e.printStackTrace();
				continue;
			}
		}
		int rng1 = new Random().nextInt(dnaList.size());
		int rng2 = rng1;
		while (rng1 == rng2) {
			rng2 = new Random().nextInt(dnaList.size());
		}
		DNA result = new DNA(dnaList.get(rng1), dnaList.get(rng2));
		Utils.saveParentAndDNAToFile(dnaList.get(rng1), dnaList.get(rng2), result);
		System.out.println(result);
	}


}