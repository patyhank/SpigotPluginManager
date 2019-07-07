package net.Lenni0451.SpigotPluginManager;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.Lenni0451.SpigotPluginManager.commands.PluginManager_Command;
import net.Lenni0451.SpigotPluginManager.commands.Reload_Command;
import net.Lenni0451.SpigotPluginManager.tabcomplete.PluginManager_TabComplete;
import net.Lenni0451.SpigotPluginManager.utils.DownloadUtils;
import net.Lenni0451.SpigotPluginManager.utils.Logger;
import net.Lenni0451.SpigotPluginManager.utils.PluginUtils;

public class PluginManager extends JavaPlugin {
	
	private static PluginManager instance;
	
	public static PluginManager getInstance() {
		return instance;
	}
	
	
	private final PluginUtils pluginUtils;
	
	public PluginManager() {
		instance = this;
		
		this.saveDefaultConfig();
		
		this.pluginUtils = new PluginUtils();
	}
	
	public PluginUtils getPluginUtils() {
		return this.pluginUtils;
	}
	
	
	@Override
	public void onEnable() {
		this.getCommand("reload").setExecutor(new Reload_Command());
		this.getCommand("reload").setAliases(Arrays.asList("rl"));
		
		this.getCommand("pluginmanager").setExecutor(new PluginManager_Command());
		this.getCommand("pluginmanager").setAliases(Arrays.asList("pm"));
		this.getCommand("pluginmanager").setTabCompleter(new PluginManager_TabComplete());
		
		try {
			String newestVersion = DownloadUtils.getNewestVersion();
			if(!newestVersion.equals(this.getDescription().getVersion())) {
				Logger.sendPrefixMessage(Bukkit.getConsoleSender(), "A new update of PluginManager is available �e(" + this.getDescription().getVersion() + " -> " + newestVersion + ").");
				Logger.sendPrefixMessage(Bukkit.getConsoleSender(), "You can download it here: �6https://github.com/Lenni0451/SpigotPluginManager/releases/latest/download/PluginManager.jar");
			} else {
				Logger.sendPrefixMessage(Bukkit.getConsoleSender(), "You are using the latest version of PluginManager.");
			}
		} catch (Throwable e) {
			Logger.sendPrefixMessage(Bukkit.getConsoleSender(), "�cCould not check for updates.");
		}
	}
	
}
