package com.existingeevee.chickeneer.discord.commands;

import java.util.Map.Entry;

import com.existingeevee.chickeneer.discord.Command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;

public class CommandTest extends Command {


	public CommandTest() {
		super("test", "Used for testing Usage: >test");
	}

	@Override
	public void run(MessageReceivedEvent event) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setColor(0x00FF00);
		eb.setTitle("Test Screen");
		eb.setDescription("Time to break GMO laws!!!");

		SelectionMenu.Builder builder = SelectionMenu.create("chickeneer:testid:" + event.getAuthor().getId());
		builder.addOption("Label", "value", "description", Emoji.fromEmote("hp", 873725533228236800L, false)).build();
		builder.addOption("you should pick this", "fing your mom", "hehe", Emoji.fromEmote("hp", 873725533228236800L, false)).build();
		builder.addOption("megaman", "yo mother", "nutbag", Emoji.fromEmote("hp", 873725533228236800L, false)).build();
		builder.addOption("Test", "ing your mom", "nut", Emoji.fromEmote("hp", 873725533228236800L, false)).build();
		event.getChannel().sendMessage(eb.build()).setActionRow(builder.build()).queue();
	}
	@Override
	public boolean canUse(User user) {
		return true;
	}

}
