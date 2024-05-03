package me.ranwolf.zerobingo;

import lombok.Getter;
import lombok.Setter;
import me.ranwolf.zerobingo.manager.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ZeroBingo extends JavaPlugin {

    @Getter private static ZeroBingo instance;
    @Getter @Setter private GameManager gameManager;
    
    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        gameManager = new GameManager();
        gameManager.loadCmd();
        gameManager.loadListeners();
        gameManager.loadCompleters();
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
