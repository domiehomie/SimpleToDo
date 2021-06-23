package live.mufin.simpletodo.commands;

import live.mufin.MufinCore.ConfigFile;
import live.mufin.MufinCore.commands.MCM;
import live.mufin.simpletodo.SimpleTodo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class ScrapCommand implements CommandExecutor, MCM {
    @Override
    public String commandName() {
        return "scrap";
    }

    @Override
    public String[] commandAliases() {
        return null;
    }

    @Override
    public String usage() {
        return "/scrap <item>";
    }

    @Override
    public String description() {
        return "Scraps the item from your todo list!";
    }

    @Override
    public String permission() {
        return "todo.scrap";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if(args.length < 1) SimpleTodo.core.sendFormattedMessage(sender, "&cPlease supply an item as a number.");
        try {
            int i = Integer.parseInt(args[0]);
            FileConfiguration config = SimpleTodo.cnf.getConfig();
            if(!config.contains(p.getUniqueId() + ".tasks")) SimpleTodo.core.sendFormattedMessage(sender, "&cYou do not have any tasks.");
            List<String> items = config.getStringList(p.getUniqueId() + ".tasks");
            String item = items.get(i - 1);
            items.remove(i - 1);
            config.set(p.getUniqueId() + ".tasks", items);
            SimpleTodo.core.sendFormattedMessage(sender, "Successfully removed &e" + i + " &7from your ToDo list.");
        } catch (IllegalArgumentException e) {
            SimpleTodo.core.sendFormattedMessage(sender, "&cPlease supply a valid item as a number.");
        }
        return true;
    }
}
