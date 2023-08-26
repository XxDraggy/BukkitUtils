package com.xxdraggy.utils.builders;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class CommandBuilder {
    public CommandBuilder() {}
    public CommandBuilder(String command) {
        this.setCommand(command);
    }
    private CommandBuilder(Command data, Command command) {
        this.data = data;
        this.command = command;
    }

    public CommandBuilder clone() {
        return new CommandBuilder(data, command);
    }

    private Command data = new Command("") {
        @Override
        public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
            return false;
        }
    };
    private Command command;

    public CommandBuilder setAliases(String... aliases) {
        data.setAliases(Arrays.asList(aliases));

        return this;
    }
    public CommandBuilder addAliases(String... aliases) {
        List<String> newAliases = command.getAliases();
        newAliases.addAll(Arrays.asList(aliases));

        data.setAliases(newAliases);

        return this;
    }
    public CommandBuilder addAlias(String alias) {
        List<String> newAliases = command.getAliases();
        newAliases.add(alias);

        data.setAliases(newAliases);

        return this;
    }

    public CommandBuilder setExecutor(BiFunction<CommandSender, String[], Boolean> executor) {
        command = new Command("") {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                return executor.apply(sender, args);
            }
        };

        return this;
    }

    public CommandBuilder setCommand(String command) {
        data.setName(command);

        return this;
    }

    public CommandBuilder setDescription(String description) {
        data.setDescription(description);

        return this;
    }

    public CommandBuilder setUsage(String usage) {
        data.setUsage(usage);

        return this;
    }

    public CommandBuilder setPermission(String permission) {
        data.setPermission(permission);

        return this;
    }

    public CommandBuilder setPermissionMessage(String message) {
        data.setPermissionMessage(message);

        return this;
    }

    public void register() {
        command.setAliases(data.getAliases());
        command.setName(data.getName());
        command.setDescription(data.getDescription());
        command.setUsage(data.getUsage());
        command.setPermission(data.getPermission());
        command.setPermissionMessage(data.getPermissionMessage());

        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            field.setAccessible(true);

            CommandMap map = (CommandMap) field.get(Bukkit.getServer());

            map.register(command.getName(), command);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }
}
