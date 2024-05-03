package me.ranwolf.zerobingo.cmd;

import me.ranwolf.zerobingo.ZeroBingo;
import me.ranwolf.zerobingo.manager.GameManager;
import me.ranwolf.zerobingo.manager.PlayerManager;
import me.ranwolf.zerobingo.manager.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class startGame implements CommandExecutor {

    ZeroBingo plugin = ZeroBingo.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (plugin.getGameManager().isGameOn()){
            sender.sendMessage("Game is already on!");
            return true;
        }
        plugin.getGameManager().setGameOn(true);
        if (args.length >= 1){
            if (args[0].equalsIgnoreCase("teams")){
                startTeamMode();
                plugin.getGameManager().setTeamMode(true);
                return true;
            }
        }

        HashMap<UUID , PlayerManager> players = plugin.getGameManager().getPlayers();
        for (Player player : Bukkit.getOnlinePlayers())
            players.put(player.getUniqueId() , new PlayerManager(player.getUniqueId()));
        plugin.getGameManager().setPlayers(players);
        return true;
    }


    public void startTeamMode() {

        // Creating all teams
        for (int i = 1; i <= plugin.getGameManager().getHowMuchTeams(); i++) {
            HashMap<Integer, TeamManager> teams = plugin.getGameManager().getTeams();
            TeamManager team = new TeamManager(i);
            teams.put(i, team);
            plugin.getGameManager().setTeams(teams);
        }

        // giving players team.
        int teamPlayersNum = Bukkit.getOnlinePlayers().size() / plugin.getGameManager().getHowMuchTeams();
        int playerNumNow = 0;
        int teamNow = 1;
        for (Player player : Bukkit.getOnlinePlayers()) {

            if (playerNumNow == teamPlayersNum) {
                teamNow++;
                playerNumNow = 0;
            }

            if (teamNow > plugin.getGameManager().getHowMuchTeams())
                teamNow = plugin.getGameManager().getHowMuchTeams();

            ArrayList<UUID> players = plugin.getGameManager().getTeams().get(teamNow).getTeamPlayers();
            players.add(player.getUniqueId());
            plugin.getGameManager().getTeams().get(teamNow).setTeamPlayers(players);
            playerNumNow++;
        }
    }
}
