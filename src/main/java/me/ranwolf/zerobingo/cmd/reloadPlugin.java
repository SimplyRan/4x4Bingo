package me.ranwolf.zerobingo.cmd;

import me.ranwolf.zerobingo.ZeroBingo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class reloadPlugin implements CommandExecutor {

    ZeroBingo plugin = ZeroBingo.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.reloadConfig();
        return false;
    }
}
