package com.github.allinkdev.esp;

import com.earth2me.essentials.CommandSource;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import me.totalfreedom.scissors.event.player.SpectatorTeleportEvent;
import me.totalfreedom.totalfreedommod.TotalFreedomMod;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static com.earth2me.essentials.I18n.tl;

public final class Main extends JavaPlugin implements Listener {
    private TotalFreedomMod tfm;
    private Essentials essentials;

    @Override
    public void onEnable() {
        // The only safe place to get dependency plugins is in onEnable
        this.tfm = TotalFreedomMod.getPlugin();
        this.essentials = JavaPlugin.getPlugin(Essentials.class);

        final PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void onSpectatorTeleport(final SpectatorTeleportEvent event) {
        final Player player = event.getPlayer();
        final boolean isAdmin = tfm.al.isAdmin(player);

        // We want to allow admins to bypass TPToggle in spectator mode
        if (isAdmin) {
            return;
        }

        final Entity target = event.getTarget();

        // We don't care if a player spectates, say, a cow
        if (!(target instanceof final Player targetPlayer)) {
            return;
        }

        final User essentialsUser = essentials.getUser(targetPlayer);

        if (essentialsUser.isTeleportEnabled()) {
            return;
        }

        final CommandSource commandSource = new CommandSource(player);
        final String displayName = targetPlayer.getDisplayName();
        final String message = tl("teleportDisabled", displayName);
        final Exception exception = new Exception(message);

        essentials.showError(commandSource, exception, "tp");
        event.setCancelled(true);
    }
}
