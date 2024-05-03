package me.ranwolf.zerobingo.cmd;

import me.ranwolf.zerobingo.ZeroBingo;
import me.ranwolf.zerobingo.manager.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class endGame implements CommandExecutor{

    ZeroBingo plugin = ZeroBingo.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.setGameManager(new GameManager());
        return true;
    }
}
