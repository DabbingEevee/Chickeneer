package com.existingeevee.chickeneer.misc.discord.events;

import com.existingeevee.chickeneer.misc.discord.Command;
import com.existingeevee.chickeneer.misc.discord.commands.CommandHelp;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnReady extends ListenerAdapter {

	@Override
	public void onReady(ReadyEvent event) {
		System.out.println("Load Complete. Logged in as " + event.getJDA().getSelfUser().getAsTag() + " with ID: "
				+ event.getJDA().getSelfUser().getId());
		System.out.println("=====Loading-Complete=====");

		//System.out.println(GlobalVariables.alloys);

		System.out.println();
		System.out.println("=====Registering-Commands=====");
		registerCommands();
		System.out.println("=====Registration-Complete=====");
		System.out.println("");
	}

	public static void registerCommands() {
		Command.register(new CommandHelp());
	}

}
