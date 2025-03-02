package com.craftaro.skyblock.permission.permissions.listening;

import com.craftaro.skyblock.SkyBlock;
import com.craftaro.skyblock.island.Island;
import com.craftaro.skyblock.island.IslandManager;
import com.craftaro.skyblock.island.IslandRole;
import com.craftaro.skyblock.permission.*;
import com.craftaro.third_party.com.cryptomorin.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.util.HashMap;

public class SignEditPermission extends ListeningPermission implements Listener {

    private final SkyBlock plugin;

    public SignEditPermission(SkyBlock plugin) {
        super("EditSign", XMaterial.OAK_SIGN, PermissionType.GENERIC, new HashMap<IslandRole, Boolean>() {{
            put(IslandRole.VISITOR, false);
            put(IslandRole.MEMBER, true);
            put(IslandRole.OPERATOR, true);
            put(IslandRole.COOP, true);
            put(IslandRole.OWNER, true);
        }});
        this.plugin = plugin;
    }

    @PermissionHandler
    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        Player player = event.getPlayer();
        IslandManager islandManager = plugin.getIslandManager();
        Island island = islandManager.getIslandAtLocation(event.getBlock().getLocation());

        if (island == null) {
            return;
        }

        PermissionManager permissionManager = plugin.getPermissionManager();
        BasicPermission EditSign = permissionManager.getPermission("EditSign");
        boolean hasPermission = permissionManager.hasPermission(player, island, EditSign);

        if (!hasPermission) {
            cancelAndMessage(event, player, this.plugin, this.plugin.getMessageManager());
        }
    }
}
