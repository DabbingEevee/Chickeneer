package com.existingeevee.chickeneer.discord.events;

import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnInteraction extends ListenerAdapter {

	@Override
	public void onSelectionMenu(SelectionMenuEvent event) {
		try {
			if (event.getUser().getId().equalsIgnoreCase(event.getSelectionMenu().getId().split(":")[2])) {
				event.reply(event.getSelectedOptions().get(0).getValue()).queue();
			} else {
				event.deferReply(false);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			event.deferReply(false);
		}
	}
}
