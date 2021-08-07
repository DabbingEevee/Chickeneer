package com.existingeevee.chickeneer.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.existingeevee.chickeneer.genetics.Allele;
import com.existingeevee.chickeneer.genetics.AlleleSerializer;
import com.existingeevee.chickeneer.genetics.DNA;
import com.existingeevee.chickeneer.genetics.Trait;
import com.existingeevee.chickeneer.misc.Utils;
import com.existingeevee.chickeneer.misc.discord.ChickeneerDiscordBot;

import net.dv8tion.jda.api.entities.User;

public class Chicken {

	private String ownerID = null;

	private DNA chickenDNA; 

	public Chicken(DNA dna) {
		this.chickenDNA = dna;
	}
	
	public Chicken saveChickenToFile() {
		Utils.saveChickenToFile(this);
		return this;
	}

	public UUID retrieveChickenUUID() {
		return this.chickenDNA.getUUID();
	}

	public Chicken breedChicken(Chicken chicken) {
		DNA dna = new DNA(this.getChickenDNA(), chicken.getChickenDNA());
		Chicken child = new Chicken(dna);
		Utils.logParentsToFile(this, chicken, child);
		return child;
	}

	public DNA getChickenDNA() {
		return chickenDNA;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public User getOwner() {
		if (ownerID != null) {
			try {
				ChickeneerDiscordBot.jda.retrieveUserById(ownerID);
			} catch (Throwable e) {
				return null;
			}
		}
		return null;
	}

	public void setOwner(User user) {
		if (user != null) {
			user.getId();
		}
	}
	
	public static Chicken fromExistingChickenFolder(File folderFile) {
		if (folderFile.isDirectory()) {
			File dnaDir = new File(folderFile.getPath() + "/dna");
			List<Trait> list = new ArrayList<Trait>();
			if (dnaDir.exists() && dnaDir.isDirectory()) {
				for (File tr : dnaDir.listFiles()) {
					if (tr.isDirectory()) {
						if (new File(tr.getPath() + "/traitdata.json").exists()) {
							Map<String,String> json = Utils.readJsonFileFromPath(tr.getPath() + "/traitdata.json");
							Map<String,String> alleleA = Utils.readJsonFileFromPath(tr.getPath() + "/a.json");
							Map<String,String> alleleB = Utils.readJsonFileFromPath(tr.getPath() + "/b.json");
							list.add(new Trait(
									tr.getName(), 
									AlleleSerializer.serializers.get(alleleA.get("serializer")).serializeFromJson(Utils.readFileFromPath(tr.getPath() + "/a.json")), 
									AlleleSerializer.serializers.get(alleleB.get("serializer")).serializeFromJson(Utils.readFileFromPath(tr.getPath() + "/b.json")), 
									new Random(), 
									json.get("dominant").equalsIgnoreCase("a")
								));
						} else {
							System.err.println("Not a trait in DNA folder: " + tr.getName());
						}
					} else {
						System.err.println("Not a trait in DNA folder: " + tr.getName());
					}
					Chicken chicken = new Chicken(new DNA((Trait[]) list.toArray()));
					Map<String,String> json = Utils.readJsonFileFromPath(tr.getPath() + "/traitdata.json");

					return 
				}
			} else {
				throw new IllegalArgumentException("Invalid Data Structure for chicken folder: Missing or invalid DNA.");
			}
		} else {
			throw new IllegalArgumentException("Invalid Data Structure for chicken folder: File is not a folder.");
		}
	}
}
