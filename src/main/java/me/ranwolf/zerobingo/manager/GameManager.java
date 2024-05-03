package me.ranwolf.zerobingo.manager;

import lombok.Getter;
import lombok.Setter;
import me.ranwolf.zerobingo.ZeroBingo;
import me.ranwolf.zerobingo.cmd.*;
import me.ranwolf.zerobingo.cmd.teamCmd.addToTeam;
import me.ranwolf.zerobingo.cmd.teamCmd.teams;
import me.ranwolf.zerobingo.completers.addItem;
import me.ranwolf.zerobingo.completers.addToTeamCom;
import me.ranwolf.zerobingo.listeners.disableTakingItems;
import me.ranwolf.zerobingo.listeners.pickupItem;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class GameManager {

    ZeroBingo plugin = ZeroBingo.getInstance();
    @Getter int howMuchTeams = 2;
    @Getter @Setter HashMap<Integer , TeamManager> teams;
    @Getter @Setter HashMap<UUID, PlayerManager> players;
    @Getter @Setter boolean gameOn;
    @Getter @Setter boolean teamMode;

    public GameManager(){
        this.players = new HashMap<>();
        this.teams = new HashMap<>();
        this.gameOn = false;
        this.teamMode = false;
    }

    public void loadListeners(){
        plugin.getServer().getPluginManager().registerEvents(new pickupItem() , plugin);
        plugin.getServer().getPluginManager().registerEvents(new disableTakingItems() , plugin);
        plugin.getServer().getPluginManager().registerEvents(new teams() , plugin);
    }
    public void loadCmd(){
        plugin.getCommand("startbingo").setExecutor(new startGame());
        plugin.getCommand("endbingo").setExecutor(new endGame());
        plugin.getCommand("bingo").setExecutor(new bingoBored());
        plugin.getCommand("additem").setExecutor(new addItemToBingo());
        plugin.getCommand("reloadbingo").setExecutor(new reloadPlugin());
        plugin.getCommand("bingoteams").setExecutor(new teams());
        plugin.getCommand("addtoteam").setExecutor(new addToTeam());

    }
    public void loadCompleters(){
        plugin.getCommand("additem").setTabCompleter(new addItem());
        plugin.getCommand("addtoteam").setTabCompleter(new addToTeamCom());
    }

    public int getPlayerTeam(Player player){
        for (int teamNum : teams.keySet()){
            if (teams.get(teamNum).teamPlayers.contains(player.getUniqueId()))
                return teamNum;
        }
        return -1;
    }


}
