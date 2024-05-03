package me.ranwolf.zerobingo.cmd.teamCmd;

import me.ranwolf.zerobingo.ZeroBingo;
import me.ranwolf.zerobingo.manager.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class addToTeam implements CommandExecutor {

    ZeroBingo plugin = ZeroBingo.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!plugin.getGameManager().isGameOn()) {
            sender.sendMessage("Game isn't on!");
            return true;
        }
        if (!plugin.getGameManager().isTeamMode()){
            sender.sendMessage("Team mode isn't on!");
            return true;
        }

        if (args.length < 2)
            return false;
        if (Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage("Invalid player!");
            return true;
        }
        if (!tryGettingNumber(args[1])) {
            sender.sendMessage("Invalid number! pick number from 1-" + plugin.getGameManager().getHowMuchTeams());
            return true;
        }

        HashMap<Integer , TeamManager> teams = plugin.getGameManager().getTeams();
        Player player = Bukkit.getPlayer(args[0]);
        int teamMoveNumber = Integer.parseInt(args[1]);
        int currentTeamNumber = plugin.getGameManager().getPlayerTeam(player);
        if (teamMoveNumber == currentTeamNumber){

        }

        TeamManager playerCurrentTeam = teams.get(currentTeamNumber);
        TeamManager playerMoveTeam = teams.get(teamMoveNumber);

        playerCurrentTeam.getTeamPlayers().remove(player.getUniqueId());
        playerMoveTeam.getTeamPlayers().add(player.getUniqueId());

        teams.put(currentTeamNumber , playerCurrentTeam);
        teams.put(teamMoveNumber , playerMoveTeam);

        plugin.getGameManager().setTeams(teams);

        return true;
    }

    public boolean tryGettingNumber(String number){
        try {
            Integer num = Integer.parseInt(number);
            if (num > plugin.getGameManager().getHowMuchTeams() || num < 1){
                return false;
            }
            return true;
        }catch (NumberFormatException e){
            e.getStackTrace();
        }
        return false;
    }

}
