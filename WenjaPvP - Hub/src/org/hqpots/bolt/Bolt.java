package org.hqpots.bolt;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.Team;
import org.hqpots.bolt.commands.SetSpawnCommand;
import org.hqpots.bolt.events.PlayerEvents;
import org.hqpots.bolt.selector.ServerSelector;
import org.hqpots.bolt.tab.TabListener;
import org.hqpots.bolt.utils.Config;

import com.alexandeh.kraken.Kraken;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Bolt extends JavaPlugin implements Listener, PluginMessageListener
{
	public static Team team;

	private static Bolt instance;
	public static FileConfiguration config;
	public static File conf;
	public String wngnq;
	private Config mainConfig;
	public static Object ghost;
	PluginManager manager = Bukkit.getServer().getPluginManager();

	public static Bolt getInstance()
	{
		return instance;
	}

	public Bolt()
	{
		this.wngnq = "Incorrect HWID! Disabling plugin. If you need an HWID, get one from Grimy! E-Mail grimyplugins@gmail.com or message Angelease on SpigotMC or MCMarket.";
	}

	@Override
	public void onEnable()
	{
		instance = this;

		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Fuck enabled.");
		new Kraken(this);
		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		this.getAPI();
		config = getConfig();
		config.options().copyDefaults(true);
		conf = new File(getDataFolder(), "config.yml");
		saveConfig();
		saveDefaultConfig();

		getCommand("setspawn").setExecutor(new SetSpawnCommand(this));

		manager.registerEvents(new PlayerEvents(), this);
		manager.registerEvents(new ServerSelector(), this);
		manager.registerEvents(new TabListener(), this);

		/**
		 * 
		 * HWID Whitelist
		 * 
		 */
		
		(this.mainConfig = new Config(this, "", "config")).setDefault("Shit", false);
		this.mainConfig.setDefault("hwid", "");
	}

	@Override
	public void onDisable()
	{
		instance = null;
	}

	public byte[] getOnlinePlayers()
	{
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("PlayerCount");
		out.writeUTF("ALL");
		return out.toByteArray();
	}

	private void wqminoiwn()
	{
		Bukkit.getPluginManager().disablePlugin((Plugin) this);
	}

	public void getAPI()
	{
		try
		{
			final URL url = new URL("https://pastebin.com/raw/mNQ2gwLL");
			final ArrayList<Object> lines = new ArrayList<Object>();
			final URLConnection connection = url.openConnection();
			final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null)
			{
				lines.add(line);
			}
			if (!lines.contains(this.getConfig().getString("hwid")) && this.getConfig().getString("hwid") != null)
			{
				this.getLogger().log(Level.SEVERE, this.wngnq);
				this.wqminoiwn();
			}
			else if (this.getConfig().getString("hwid") == null)
			{
				this.getLogger().log(Level.SEVERE, "Add an HWID in the config!");
				this.wqminoiwn();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.getLogger().log(Level.SEVERE, "Error! Disabling plugin.");
			Bukkit.getPluginManager().disablePlugin((Plugin) this);
		}
	}

	@Override
	public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2)
	{}
}