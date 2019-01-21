package org.hqpots.bolt.utils;

import org.bukkit.plugin.java.*;
import java.io.*;
import org.bukkit.configuration.file.*;

public class Config
{
	private FileConfiguration Config;
	private File File;
	private String Name;

	public Config(final JavaPlugin Plugin, final String Path, final String Name)
	{
		(this.File = new File(Plugin.getDataFolder() + Path)).mkdirs();
		this.File = new File(Plugin.getDataFolder() + Path, String.valueOf(Name) + ".yml");
		try
		{
			this.File.createNewFile();
		}
		catch (IOException ex)
		{}
		this.Name = Name;
		this.Config = (FileConfiguration) YamlConfiguration.loadConfiguration(this.File);
	}

	public String getName()
	{
		return this.Name;
	}

	public FileConfiguration getConfig()
	{
		return this.Config;
	}

	public void setDefault(final String Path, final Object Set)
	{
		if (!this.getConfig().contains(Path))
		{
			this.Config.set(Path, Set);
			this.save();
		}
	}

	public void save()
	{
		try
		{
			this.Config.save(this.File);
		}
		catch (IOException ex)
		{}
	}

	public void reload()
	{
		this.Config = (FileConfiguration) YamlConfiguration.loadConfiguration(this.File);
	}
}