package me.wilux.blockshelf.util;

import me.wilux.blockshelf.main.CustomUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config
{
	public static YamlConfiguration cfg;
	public static File cfgFile;
	
	public static YamlConfiguration getConfiguration()
	{
		return cfg;
	}
	
	public static File getConfigurationFile()
	{
		return cfgFile;
	}
	
	public static void save()
	{
		try
		{
			cfg.save(cfgFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void load()
	{
		try
		{
			cfg.load(cfgFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void onEnable()
	{
		cfgFile = new File(CustomUtils.instance.getDataFolder(), "cfg.yml");
		if(!cfgFile.exists())
			try {
				cfgFile.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
		cfg = YamlConfiguration.loadConfiguration(cfgFile);
		
		setStandardConfigValues();
	}
	
	public static void onDisable()
	{
		try {
			cfg.save(cfgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void setStandardConfigValues()
	{
		setDefault("send-resourcepack-request", true);
		setDefault("init-method", "minepack");

		try {
			cfg.save(cfgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void setDefault(String key, Object value)
	{
		if(!cfg.contains(key))
		cfg.set(key, value);
	}
	
}
