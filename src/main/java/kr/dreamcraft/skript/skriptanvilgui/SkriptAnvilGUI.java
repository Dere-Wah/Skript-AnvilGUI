package kr.dreamcraft.skript.skriptanvilgui;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class SkriptAnvilGUI extends JavaPlugin {

    static SkriptAnvilGUI instance;
    SkriptAddon addon;
    public static FileConfiguration config;

    public void onEnable(){
        instance = this;
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("kr.dreamcraft.skript.skriptanvilgui");
        } catch(IOException e){
            e.printStackTrace();
        }
        addon.setLanguageFileDirectory("lang");

        Bukkit.getLogger().info("[Skript-AnvilGUI] has been enabled!");
    }


    public static SkriptAnvilGUI getInstance(){
        return instance;
    }

    public SkriptAddon getAddonInstance(){
        return addon;
    }
}