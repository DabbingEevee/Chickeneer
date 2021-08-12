package com.existingeevee.chickeneer.discord.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.existingeevee.chickeneer.data.Chicken;
import com.existingeevee.chickeneer.discord.Command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;

public class CommandChickenList extends Command{


	public CommandChickenList() {
		super("list", "Lists all chicken. Usage: }list");
	}

	@Override
	public void run(MessageReceivedEvent event) {
		List<Chicken> chickenList = new ArrayList<Chicken>();
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
		
		EmbedBuilder eb = new EmbedBuilder();
		eb.setColor(0x00FF00);
		eb.setTitle("Chicken Screen");
		eb.setDescription("Time to break GMO laws!!!");

		SelectionMenu.Builder builder = SelectionMenu.create("chickeneer:list:" + event.getAuthor().getId());
		builder.addOption("Label", "value", "description", Emoji.fromEmote("hp", 873725533228236800L, false)).build();

		for (Chicken chicken : chickenList) {
			builder.addOption(chicken.getChickenDNA().getUUID().toString(), "value", chicken.getChickenDNA().toString(), Emoji.fromEmote("hp", 873725533228236800L, false)).build();
		}
		
		event.getChannel().sendMessage(eb.build()).setActionRow(builder.build()).queue();
	}
	@Override
	public boolean canUse(User user) {
		return true;
	}
}
