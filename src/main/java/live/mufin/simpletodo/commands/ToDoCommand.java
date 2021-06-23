package live.mufin.simpletodo.commands;

import live.mufin.MufinCore.commands.MCM;
import live.mufin.simpletodo.SimpleTodo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ToDoCommand implements CommandExecutor, MCM {
    @Override
    public String commandName() {
        return "todo";
    }

    @Override
    public String[] commandAliases() {
        return new String[]{"td"};
    }

    @Override
    public String usage() {
        return "/todo [task]";
    }

    @Override
    public String description() {
        return "Get a list of things in your todo list!";
    }

    @Override
    public String permission() {
        return "todo.todocmd";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if(args.length > 0) {
            if(SimpleTodo.cnf.getConfig().contains(p.getUniqueId() + ".tasks")){
                List<String> items = SimpleTodo.cnf.getConfig().getStringList(p.getUniqueId() + ".tasks");
                items.add(String.join(" ", args));
                SimpleTodo.cnf.getConfig().set(p.getUniqueId() + ".tasks", items);
            } else {
                List<String> items = new ArrayList<>();
                items.add(String.join(" ", args));
                SimpleTodo.cnf.getConfig().set(p.getUniqueId() + ".tasks", items);
            }
            SimpleTodo.cnf.saveConfig();
            SimpleTodo.core.sendFormattedMessage(sender, "Successfully added &" + SimpleTodo.core.color + String.join(" ", args) + "&7 to your todo list.");
        } else {
            if(SimpleTodo.cnf.getConfig().contains(p.getUniqueId() + ".tasks")) {
                SimpleTodo.core.sendFormattedMessage(p, "&8=============================");
                SimpleTodo.core.sendFormattedMessage(sender, "Tasks for &" + SimpleTodo.core.color + p.getName() + "&7:");
                List<String> items = SimpleTodo.cnf.getConfig().getStringList(p.getUniqueId() + ".tasks");
                for (int i = 0; i < items.size(); i++) {
                    SimpleTodo.core.sendFormattedMessage(sender, (i + 1) + ": &" + SimpleTodo.core.color + items.get(i));
                }
                SimpleTodo.core.sendFormattedMessage(p, "&8=============================");
            } else {
                SimpleTodo.core.sendFormattedMessage(sender, "&cYou do not have any tasks! Create one using /todo [task]");
            }
        }
        return true;
    }
}
