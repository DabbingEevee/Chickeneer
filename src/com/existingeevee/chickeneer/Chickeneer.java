package com.existingeevee.chickeneer;

import java.util.ArrayList;
import java.util.List;

import com.existingeevee.chickeneer.data.Chicken;
import com.existingeevee.chickeneer.discord.ChickeneerDiscordBot;

public class Chickeneer {

	public static List<Chicken> chickenList = new ArrayList<Chicken>();

	public static void main(String[] args) {
		ChickeneerDiscordBot.startDiscordBot();

		/*File folder = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "user");
		for (File e : folder.listFiles()) {
			if (e.isDirectory())
				System.out.println(e.getPath() + "/chickens");
				for (File f : new File(e.getPath() + "/chickens").listFiles()) {
					try {
						UUID.fromString(f.getName());
						if (!f.isDirectory()) {
							continue;
						} else {
							DNA dna = new DNA(UUID.fromString(f.getName()));

							for (File t : new File(f.getPath() + "/dna").listFiles()) {
								try {
									if (!t.isDirectory()) {
										continue;
									} else {
										Allele<?> a = Utils.deserializeAllele(Utils.fileread("user/" + e.getName() +
												"/chickens/" + f.getName() + "/dna/" + t.getName() + "/a.json"));
										Allele<?> b = Utils.deserializeAllele(Utils.fileread("user/" + e.getName() +
												"/chickens/" + f.getName() + "/dna/" + t.getName() + "/b.json"));
										Map<String, String> map = Utils.readJsonFile("user/" + e.getName() +
												"/chickens/" + f.getName() + "/dna/" + t.getName() + "/traitdata");
										Trait tr = new Trait(t.getName(), a, b, new Random(),
												map.get("dominant").equalsIgnoreCase("a") ? true : false);
										dna.getTraitMap().put(tr.getIdentifier(), tr);
									}
								} catch (Throwable er) {
									er.printStackTrace();
									continue;
								}
							}
							//dnaList.add(dna);
						}
					} catch (Throwable er) {
						er.printStackTrace();
						continue;
					}
				}
		}
		//int rng1 = new Random().nextInt(dnaList.size());
		//int rng2 = rng1;
		//while (rng1 == rng2) {
			//rng2 = new Random().nextInt(dnaList.size());
		//}
		//DNA result = new DNA(dnaList.get(rng1), dnaList.get(rng2));
		//Utils.logParentsToFile(new Chicken(dnaList.get(rng1)), new Chicken(dnaList.get(rng2)), new Chicken(result));
		//System.out.println(result);*/
	}
}