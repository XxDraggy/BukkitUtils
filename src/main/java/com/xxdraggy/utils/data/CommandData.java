package com.xxdraggy.utils.data;

import com.xxdraggy.utils.command.Parameter;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class CommandData {
    private List<CommandData> subCommands = new ArrayList<>();
    public List<CommandData> getSubcommands() {
        return subCommands;
    }
    public void setSubCommands(List<CommandData> subCommands) {
        this.subCommands = subCommands;
    }
    public void addSubCommands(List<CommandData> subCommands) {
        this.subCommands.addAll(subCommands);
    }

    private String command;
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }

    private BiFunction<CommandSender, String[], Boolean> executor;
    public BiFunction<CommandSender, String[], Boolean> getExecutor() {
        return executor;
    }
    public void setExecutor(BiFunction<CommandSender, String[], Boolean> executor) {
        this.executor = executor;
    }

    private List<String> aliases = new ArrayList<>();
    public List<String> getAliases() {
        return aliases;
    }
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }
    public void addAliases(List<String> aliases) {
        this.aliases.addAll(aliases);
    }

    private String usage;
    public String getUsage() {
        return usage;
    }
    public void setUsage(String usage) {
        this.usage = usage;
    }

    private String permission;
    public String getPermission() {
        return permission;
    }
    public void setPermission(String permission) {
        this.permission = permission;
    }

    private String permissionMessage;
    public String getPermissionMessage() {
        return permissionMessage;
    }
    public void setPermissionMessage(String permissionMessage) {
        this.permissionMessage = permissionMessage;
    }

    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    private List<Parameter> parameters = new ArrayList<>();
    public List<Parameter> getParameters() {
        return parameters;
    }
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
    public void addParameters(List<Parameter> parameters) {
        this.parameters.addAll(parameters);
    }
}
