package com.existingeevee.chickeneer.misc.discord;

import java.io.File;

import javax.security.auth.login.LoginException;

import com.existingeevee.chickeneer.misc.discord.events.OnMessage;
import com.existingeevee.chickeneer.misc.discord.events.OnReady;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class ChickeneerDiscordBot extends ListenerAdapter {

	public static JDABuilder builder;

	public static JDA jda;
	public static ChickeneerDiscordBot bot;

	private static boolean discordStarted = false;

	public static ListenerAdapter onSound;
	public static ListenerAdapter onMessage;
	public static ListenerAdapter onReady;

	public void setupBot() {
		System.out.println("=====Loading-Bot=====");
		builder = JDABuilder.createDefault("ODcyOTMzMzExODA5NDY2NDAx.YQxEfw.zsB-28DkcUbJ_5Th_Vs4yKV6mJI");
		//new GrillUtils().readFiles();
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		System.out.println("Initializing script from " + jarDir.getAbsolutePath().replace("%20", " "));
		System.out.println("Created by DabbingEevee");
		builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
		builder.setBulkDeleteSplittingEnabled(false);
		builder.setCompression(Compression.NONE);
		builder.setEnabledIntents(
				GatewayIntent.DIRECT_MESSAGE_REACTIONS,
				GatewayIntent.DIRECT_MESSAGE_TYPING,
				GatewayIntent.DIRECT_MESSAGES,
				GatewayIntent.GUILD_BANS,
				GatewayIntent.GUILD_EMOJIS,
				GatewayIntent.GUILD_INVITES,
				GatewayIntent.GUILD_MEMBERS,
				GatewayIntent.GUILD_MESSAGE_REACTIONS,
				GatewayIntent.GUILD_MESSAGE_TYPING,
				GatewayIntent.GUILD_MESSAGES,
				GatewayIntent.GUILD_PRESENCES,
				GatewayIntent.GUILD_VOICE_STATES
				);

		builder.addEventListeners(

				new OnReady(),
				new OnMessage()

				);


		try {
			jda = builder.build();
		} catch (LoginException e) {
			System.out.println("Error logging into Discord with provided token");
			System.out.println();
			e.printStackTrace();
		}
	}

	public static void startDiscordBot() {
		if (discordStarted) return;
		discordStarted = true;

		ChickeneerDiscordBot discordBot = new ChickeneerDiscordBot();

		bot = discordBot;
		new ChickeneerDiscordBot().setupBot();
	}

	public static void readFiles(String id) {

	}
}
