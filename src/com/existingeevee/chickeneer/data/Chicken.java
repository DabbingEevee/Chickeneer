package com.existingeevee.chickeneer.data;

import java.io.File;
import java.util.UUID;

import com.existingeevee.chickeneer.genetics.DNA;
import com.existingeevee.chickeneer.misc.Utils;
import com.existingeevee.chickeneer.misc.discord.ChickeneerDiscordBot;

import net.dv8tion.jda.api.entities.User;

public class Chicken {

	private String ownerID = null;

	private DNA chickenDNA;

	public Chicken(DNA dna) {
		this.chickenDNA = dna;
		Utils.saveChickenToFile(this);
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
			if (dnaDir.exists() && dnaDir.isDirectory()) {
				for (File tr : dnaDir.listFiles()) {
					if (tr.isDirectory()) {
						
					} else {
						System.err.println("Error Loading Trait: ");
					}
				}
			} else {
				throw new IllegalArgumentException("Invalid Data Structure for chicken folder: Missing or invalid DNA.");
			}
		} else {
			throw new IllegalArgumentException("Invalid Data Structure for chicken folder: File is not a folder.");
		}
	}
}
