package world.ntdi.custombooks;

import org.bukkit.plugin.java.JavaPlugin;

public final class CustomBooks extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("book").setExecutor(new BookCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
