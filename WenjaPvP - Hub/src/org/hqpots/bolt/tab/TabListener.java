package org.hqpots.bolt.tab;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.hqpots.bolt.Bolt;

import com.alexandeh.kraken.tab.PlayerTab;
import com.alexandeh.kraken.tab.event.PlayerTabCreateEvent;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class TabListener implements Listener
{

	public static int Online = 1;
	public static int HCFactions = 0;
	public static int kitmap = 0;

	/**
	 * Do not touch :d
	 */

	public void onPluginMessageReceived(String channel, Player player, byte[] message)
	{
		if (!channel.equals("BungeeCord")) { return; }
		try
		{
			ByteArrayDataInput in = ByteStreams.newDataInput(message);
			String command = in.readUTF();

			if (command.equals("PlayerCount"))
			{
				String subchannel = in.readUTF();
				if (subchannel.equals("ALL"))
				{
					int playercount = in.readInt();
					Online = playercount;
				}
				if (subchannel.equals("practice"))
				{
					int playercount = in.readInt();
					HCFactions = playercount;
				}
				if (subchannel.equals("kitmap"))
				{
					int playercount = in.readInt();
					kitmap = playercount;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerTabCreateEvent(PlayerTabCreateEvent event)
	{
		Player player = event.getPlayer();

		PlayerTab playerTab = event.getPlayerTab();
		new BukkitRunnable()
		{

			@Override
			public void run()
			{
				playerTab.getByPosition(1, 0).text("&6&lWenjaPvP&7 [Hub-01]").send();
				playerTab.getByPosition(0, 4).text("&6&lTwitter:").send();
				playerTab.getByPosition(0, 5).text("&7WenjaPvP").send();

				playerTab.getByPosition(1, 4).text("&6&lYoutube:").send();
				playerTab.getByPosition(1, 5).text("&7@WenjaPvP").send();

				playerTab.getByPosition(2, 4).text("&6&lWebsite:").send();
				playerTab.getByPosition(2, 5).text("&7www.wenjapvp.net").send();

				playerTab.getByPosition(0, 7).text("&6&lPlayers:").send();
				playerTab.getByPosition(0, 8).text("&7" + Online).send();

				playerTab.getByPosition(2, 7).text("&6&lRank:").send();
				playerTab.getByPosition(2, 8).text("&cIn Development!").send();

				playerTab.getByPosition(0, 10).text("&6&lHCF:").send();
				playerTab.getByPosition(0, 11).text("&cRealese: Soon!").send();

				playerTab.getByPosition(2, 10).text("&6&lKitMap:").send();
				playerTab.getByPosition(2, 11).text("&aOnline").send();

				playerTab.getByPosition(1, 10).text("&6&lKohiSG:").send();
				playerTab.getByPosition(1, 11).text("&cOffline").send();
			}
		}.runTaskTimerAsynchronously(Bolt.getInstance(), 0L, 10L);
	}
}
