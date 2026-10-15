package io.github.schntgaispock.slimehud.waila;

import com.github.drakescraft_labs.slimefun4.api.items.SlimefunItem;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class HudRequest {
    private final @Nonnull SlimefunItem slimefunItem;
    private final @Nonnull Location location;
    private final @Nonnull Player player;

    public HudRequest(@Nonnull SlimefunItem slimefunItem, @Nonnull Location location, @Nonnull Player player) {
        if (slimefunItem == null) throw new NullPointerException("slimefunItem is marked non-null but is null");
        if (location == null) throw new NullPointerException("location is marked non-null but is null");
        if (player == null) throw new NullPointerException("player is marked non-null but is null");
        this.slimefunItem = slimefunItem;
        this.location = location;
        this.player = player;
    }

    public HudRequest(@Nonnull io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem slimefunItem, @Nonnull Location location, @Nonnull Player player) {
        this((SlimefunItem) slimefunItem, location, player);
    }

    @Nonnull
    public SlimefunItem getSlimefunItem() {
        return this.slimefunItem;
    }

    /**
     * Puente de compatibilidad binaria para addons (como BetterChests) compilados contra
     * la firma legacy/upstream: io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem getSlimefunItem()
     */
    @Deprecated
    public io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem getLegacySlimefunItem() {
        if (this.slimefunItem instanceof io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem legacy) {
            return legacy;
        }
        return null;
    }

    @Nonnull
    public Location getLocation() {
        return this.location;
    }

    @Nonnull
    public Player getPlayer() {
        return this.player;
    }
}

