package com.existingeevee.chickeneer.discord.events;

import com.existingeevee.chickeneer.discord.ChickeneerDiscordBot;
import com.existingeevee.chickeneer.discord.Command;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnMessage extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		ChickeneerDiscordBot.readFiles(event.getAuthor().getId());
		Message message = event.getMessage();
		if (!event.getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
			try {
				processCommands(event);
			} catch (InsufficientPermissionException e) {
				event.getAuthor().openPrivateChannel().complete().sendMessage(message.getAuthor().getAsMention()
						+ ", This Bot is Missing Permission to break GMO laws in #" + event.getChannel().getName() + "\n\n" + e.getMessage()).queue();
			}
		}
		super.onMessageReceived(event);
	}

	private void processCommands(MessageReceivedEvent event) {
		if (!event.getMessage().getContentRaw().startsWith("}")) return;
		if (event.getMessage().getContentRaw().length() <= 1) return ;
		Command command = Command.commandList.get(event.getMessage().getContentRaw().substring(1).split(" ")[0].toLowerCase());
		if (command.canUse(event.getAuthor())) {
			command.run(event);
		}
	}
}
