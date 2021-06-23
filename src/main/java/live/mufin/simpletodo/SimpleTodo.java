package live.mufin.simpletodo;

import live.mufin.MufinCore.ConfigFile;
import live.mufin.MufinCore.MufinCore;
import live.mufin.MufinCore.commands.MCM;
import live.mufin.simpletodo.commands.ScrapCommand;
import live.mufin.simpletodo.commands.ToDoCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleTodo extends JavaPlugin {

    public static MufinCore core;
    public static ConfigFile cnf;

    @Override
    public void onEnable() {
        core = new MufinCore(this, "SimpleToDo", ChatColor.YELLOW, "TD");

        cnf = core.initializeConfig("playerdata");

        core.registerCommands(new MCM[]{new ToDoCommand(), new ScrapCommand()});
        this.getCommand("todo").setExecutor(new ToDoCommand());
        this.getCommand("scrap").setExecutor(new ScrapCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
