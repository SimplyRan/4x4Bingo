package me.ranwolf.zerobingo.completers;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class addItem implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            return matchMaterialName(args[0]);
        }
        if (args.length == 2){
            ArrayList<String> numbersCompleter = new ArrayList<>();
            numbersCompleter.add("1");
            numbersCompleter.add("2");
            numbersCompleter.add("3");
            return numbersCompleter;
        }


        return null;
    }


    private List<String> matchMaterialName(String partial) {
        List<String> matches = new ArrayList<>();

        for (Material material : Material.values()) {
            String materialName = material.name().toUpperCase();

            if (materialName.startsWith(partial.toUpperCase())) {
                matches.add(materialName);
            }
        }

        return matches;
    }



}
