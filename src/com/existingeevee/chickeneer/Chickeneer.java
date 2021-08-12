package com.existingeevee.chickeneer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.existingeevee.chickeneer.data.Chicken;
import com.existingeevee.chickeneer.discord.ChickeneerDiscordBot;
import com.existingeevee.chickeneer.genetics.Allele;
import com.existingeevee.chickeneer.genetics.DNA;
import com.existingeevee.chickeneer.genetics.Trait;
import com.existingeevee.chickeneer.genetics.serializers.ColorAlleleSerializer;
import com.existingeevee.chickeneer.misc.Utils;

public class Chickeneer {

	public static List<Chicken> chickenList = new ArrayList<Chicken>();
	
//	public static List<DNA> dnaList = new ArrayList<DNA>();

	public static void main(String[] args) {
		ChickeneerDiscordBot.startDiscordBot();

/*		while (true) {
			if (ChickeneerDiscordBot.jda != null) break;
		}*/
		new ColorAlleleSerializer();
		File folder = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath() + "user");
		for (File e : folder.listFiles()) {
			if (e.isDirectory())
				System.out.println(e.getPath() + "/chickens");
				for (File f : new File(e.getPath() + "/chickens").listFiles()) {
					try {
						UUID.fromString(f.getName());
						if (!f.isDirectory()) {
							continue;
						} else {
							
							/*DNA dna = new DNA(UUID.fromString(f.getName()));

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
							*/
							Chicken chicken = Chicken.fromExistingChickenFolder(f);
							//chicken.setOwner(ChickeneerDiscordBot.jda.retrieveUserById(id));
							
							chickenList.add(chicken);
						}
					} catch (Throwable er) {
						er.printStackTrace();
						continue;
					}
				}
		}
		int rng1 = new Random().nextInt(chickenList.size());
		int rng2 = rng1;
		while (rng1 == rng2) {
			rng2 = new Random().nextInt(chickenList.size());
		}
		
		Chicken parent1 = chickenList.get(rng1);
		Chicken parent2 = chickenList.get(rng1);
		Chicken child   = new Chicken(new DNA(chickenList.get(rng1).getChickenDNA(), chickenList.get(rng2).getChickenDNA()));
		
		child.setOwnerID(new Random().nextBoolean() ? parent1.getOwnerID() : parent2.getOwnerID());
		
		Utils.logParentsToFile(parent1, parent2, child);
		System.out.println(child.getChickenDNA());
	}
}