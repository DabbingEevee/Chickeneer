package com.existingeevee.chickeneer.misc.discord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.existingeevee.chickeneer.misc.Utils;
import com.existingeevee.chickeneer.misc.discord.commands.CommandHelp;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

	public static Map<String,Command> commandList = new ConcurrentHashMap<String, Command>();

	private String commandName;
	private String description;

	public Command(String name, String description) {
		setCommandName(name);
		setDescription(description);
	}

	public final String getCommandName() {
		return commandName;
	}

	private final void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public final String getDescription() {
		return description;
	}

	private final void setDescription(String description) {
		this.description = description;
	}

	public abstract void run(MessageReceivedEvent event); //{
		//event.getChannel().sendMessage("This is a base command. It will do nothing").queue();
	//}

	public abstract boolean canUse(User user);

	public static void register(Command command) {
		Command previousCommand = commandList.put(command.getCommandName(), command);
		System.out.println(previousCommand == null ? "Registered Command: \"}" + Utils.capitalize(command.getCommandName()) +"\""
				: "Replaced Registered Command: \"}" + Utils.capitalize(previousCommand.getCommandName()) + "\"");
	}

}
