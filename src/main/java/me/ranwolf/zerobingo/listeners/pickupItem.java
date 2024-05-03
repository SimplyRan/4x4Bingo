package me.ranwolf.zerobingo.listeners;

import me.ranwolf.zerobingo.ZeroBingo;
import me.ranwolf.zerobingo.manager.PlayerManager;
import me.ranwolf.zerobingo.manager.TeamManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class pickupItem implements Listener {

    ZeroBingo plugin = ZeroBingo.getInstance();

    @EventHandler
    public void pickupBingoItem(PlayerPickupItemEvent e){
        if (!plugin.getGameManager().isTeamMode())
            soloBingoItem(e);
        else teamBingoItem(e);

    }

    public void soloBingoItem(PlayerPickupItemEvent e){
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        PlayerManager playerManager = plugin.getGameManager().getPlayers().get(uuid);
        HashMap<Material , Integer> playerItems = playerManager.getPlayerItems().getItems();
        Material item = e.getItem().getItemStack().getType();
        if (playerItems.containsKey(item)) {
            if (!playerManager.getPlayerItems().getGotItems().contains(item)){
                ArrayList<Material> gotItems = playerManager.getPlayerItems().getGotItems();
                gotItems.add(item);
                playerManager.getPlayerItems().setGotItems(gotItems);
                int currentPoints = playerManager.getPoints();
                playerManager.setPoints(currentPoints + playerItems.get(item));
            }
        }
    }
    public void teamBingoItem(PlayerPickupItemEvent e){
        Player player = e.getPlayer();
        int playerTeam = plugin.getGameManager().getPlayerTeam(player);
        TeamManager teamManager = plugin.getGameManager().getTeams().get(playerTeam);
        HashMap<Material , Integer> teamItems = teamManager.getTeamItems().getItems();
        Material item = e.getItem().getItemStack().getType();

        if (teamItems.containsKey(item)) {
            if (!teamManager.getTeamItems().getGotItems().contains(item)) {
                ArrayList<Material> gotItems = teamManager.getTeamItems().getGotItems();
                gotItems.add(item);
                teamManager.getTeamItems().setGotItems(gotItems);
                int currentPoints = teamManager.getPoints();
                teamManager.setPoints(currentPoints + teamItems.get(item));
            }
        }

    }

}
