package com.xxdraggy.utils.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class Command extends BukkitCommand {
    public Command(@NotNull String command, String[] aliases, String description, String permission, String permissionMessage) {
        super(command);

        setAliases(Arrays.asList(aliases));
        setDescription(description);
        setPermission(permission);
        setPermissionMessage(permissionMessage);

        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            field.setAccessible(true);

            CommandMap map = (CommandMap) field.get(Bukkit.getServer());

            map.register(command, this);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        return execute(sender, args);
    }
    public abstract boolean execute(CommandSender sender, String[] args);

    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        return onTabComplete(sender, args);
    }
    public abstract List<String> onTabComplete(CommandSender sender, String[] args);
}
