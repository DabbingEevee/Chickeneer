package com.existingeevee.chickeneer.misc.discord.commands;

import java.util.Map.Entry;

import com.existingeevee.chickeneer.misc.discord.Command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandHelp extends Command{


	public CommandHelp() {
		super("help", "Prints a list of helpful commands. Usage: >help");
	}

	@Override
	public void run(MessageReceivedEvent event) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setColor(0x00FF00);
		eb.setTitle("Help Screen");
		eb.setDescription("Time to break GMO laws!!!");

		for (Entry<String, Command> cmd : Command.commandList.entrySet()) {
			if (cmd.getValue().canUse(event.getAuthor())) {
				eb.addField(new Field("}" + cmd.getValue().getCommandName(), cmd.getValue().getDescription(), false));
			}
		}
		event.getChannel().sendMessage(eb.build()).queue();
	}
	@Override
	public boolean canUse(User user) {
		return true;
	}
}
