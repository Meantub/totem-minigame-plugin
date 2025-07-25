package com.kentubman5;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TotemMinigamePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TotemMinigamePlugin.class);
		RuneLite.main(args);
	}
}