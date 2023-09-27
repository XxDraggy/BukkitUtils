package com.xxdraggy.utils.builders.command;

import com.xxdraggy.utils.command.Parameter;
import com.xxdraggy.utils.data.CommandData;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.function.BiFunction;

public class SubCommandBuilder {
    public SubCommandBuilder() {}
    public SubCommandBuilder(String command) {
        this.setCommand(command);
    }
    private SubCommandBuilder(CommandData data) {
        this.data = data;
    }

    public SubCommandBuilder clone() {
        return new SubCommandBuilder(data);
    }

    private CommandData data = new CommandData();
    public CommandData getData() {
        return data;
    }

    public SubCommandBuilder setExecutor(BiFunction<CommandSender, String[], Boolean> executor) {
        this.data.setExecutor(executor);

        return this;
    }

    public SubCommandBuilder setCommand(String command) {
        this.data.setCommand(command);

        return this;
    }

    public SubCommandBuilder setPermission(String permission) {
        this.data.setPermission(permission);

        return this;
    }
    public SubCommandBuilder setPermissionMessage(String message) {
        this.data.setPermissionMessage(message);

        return this;
    }

    public SubCommandBuilder setParameters(Parameter... params) {
        this.data.setParameters(Arrays.asList(params));

        return this;
    }
    public SubCommandBuilder addParameters(Parameter... params) {
        this.data.addParameters(Arrays.asList(params));

        return this;
    }
    public SubCommandBuilder addParameter(Parameter param) {
        this.data.addParameters(Arrays.asList(param));

        return this;
    }
}
