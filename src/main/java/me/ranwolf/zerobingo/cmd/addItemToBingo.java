package me.ranwolf.zerobingo.cmd;

import me.ranwolf.zerobingo.ZeroBingo;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class addItemToBingo implements CommandExecutor {

    ZeroBingo plugin = ZeroBingo.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Must be player to use this command");
            return true;
        }
        if (args.length < 2){
            return false;
        }
        if (!Material.getMaterial(args[0]).isItem()){
            sender.sendMessage("Invalid Material");
            return true;
        }
        if (!tryGettingNumber(args[1])){
            sender.sendMessage("Invalid points! choose from 1-3");
            return true;
        }

        ArrayList<String> items = new ArrayList<>(plugin.getConfig().getStringList("items"));
        items.add(args[0] + "," + args[1]);
        plugin.getConfig().set("items" , items);
        plugin.saveConfig();
        sender.sendMessage("Added " + args[0] + " To bingo bored with value of " + args[1] + " points");
        plugin.reloadConfig();
        return true;
    }

    public boolean tryGettingNumber(String number){
        try {
            Integer num = Integer.parseInt(number);
            if (num > 3 || num < 1){
                return false;
            }
            return true;
        }catch (NumberFormatException e){
            e.getStackTrace();
        }
        return false;
    }

}
