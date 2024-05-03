package me.ranwolf.zerobingo.completers;

import me.ranwolf.zerobingo.ZeroBingo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class addToTeamCom implements TabCompleter {

    ZeroBingo plugin = ZeroBingo.getInstance();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 2){
            ArrayList<String> numbersCompleter = new ArrayList<>();
            for (int i = 1; i <= plugin.getGameManager().getHowMuchTeams();i++)
                numbersCompleter.add(String.valueOf(i));
            return numbersCompleter;
        }


        return null;
    }


}
