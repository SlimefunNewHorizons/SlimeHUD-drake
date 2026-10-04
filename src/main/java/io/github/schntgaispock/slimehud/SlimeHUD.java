package io.github.schntgaispock.slimehud;



import javax.annotation.Nonnull;

import io.github.schntgaispock.slimehud.placeholder.PlaceholderManager;
import io.github.schntgaispock.slimehud.translation.TranslationManager;
import io.github.schntgaispock.slimehud.waila.HudController;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.schntgaispock.slimehud.command.CommandManager;
import io.github.schntgaispock.slimehud.waila.WAILAManager;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class SlimeHUD extends JavaPlugin implements SlimefunAddon {

    @Getter YamlConfiguration playerData;
    static @Getter SlimeHUD instance;
    private HudController hudController;
    private TranslationManager translationManager;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("#=================================#");
        getLogger().info("#    SlimeHUD by SchnTgaiSpock    #");
        getLogger().info("#=================================#");

        saveDefaultConfig();
        File playerDataFile = new File(getDataFolder(), "player.yml");
        if (!playerDataFile.exists()) {
            saveResource("player.yml", false);
        }
        playerData = YamlConfiguration.loadConfiguration(playerDataFile);

        WAILAManager.setup();
        CommandManager.setup();
        PlaceholderManager.setup();
        hudController = new HudController();
        translationManager = new TranslationManager();
    }

    @Override
    public void onDisable() {
        instance = null;
        savePlayerData();
    }

    public void savePlayerData() {
        try {
            getPlayerData().save(new File(getDataFolder(), "player.yml"));
        } catch (IOException exception) {
            getLogger().warning("Could not save player HUD preferences: " + exception.getMessage());
        }
    }

    public static HudController getHudController() {
        return instance.hudController;
    }

    public static TranslationManager getTranslationManager() {
        return instance.translationManager;
    }

    public static NamespacedKey newNamespacedKey(@Nonnull String name) {
        return new NamespacedKey(SlimeHUD.getInstance(), name);
    }

    public static void log(Level level, String message, String detail) {
        SlimeHUD current = getInstance();
        if (current != null) {
            current.getLogger().log(level, message + " " + detail);
        }
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/DrakesCraft-Labs/SlimeHUD-drake/issues";
    }
}
