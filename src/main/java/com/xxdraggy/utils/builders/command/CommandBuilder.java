package com.xxdraggy.utils.builders.command;

import com.xxdraggy.utils.Creator;
import com.xxdraggy.utils.command.Parameter;
import com.xxdraggy.utils.command.ParameterType;
import com.xxdraggy.utils.data.CommandData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.logging.Level;

public class CommandBuilder {
    public CommandBuilder() {}
    public CommandBuilder(String command) {
        this.setCommand(command);
    }
    private CommandBuilder(CommandData data) {
        this.data = data;
    }

    public CommandBuilder clone() {
        return new CommandBuilder(data);
    }

    private CommandData data = new CommandData();
    public CommandData getData() {
        return data;
    }

    public CommandBuilder setAliases(String... aliases) {
        data.setAliases(Arrays.asList(aliases));

        return this;
    }
    public CommandBuilder addAliases(String... aliases) {
        List<String> newAliases = data.getAliases();
        newAliases.addAll(Arrays.asList(aliases));

        data.setAliases(newAliases);

        return this;
    }
    public CommandBuilder addAlias(String alias) {
        List<String> newAliases = data.getAliases();
        newAliases.add(alias);

        data.setAliases(newAliases);

        return this;
    }

    public CommandBuilder setExecutor(BiFunction<CommandSender, String[], Boolean> executor) {
        this.data.setExecutor(executor);

        return this;
    }

    public CommandBuilder setCommand(String command) {
        this.data.setCommand(command);

        return this;
    }

    public CommandBuilder setDescription(String description) {
        this.data.setDescription(description);

        return this;
    }

    public CommandBuilder setUsage(String usage) {
        this.data.setUsage(usage);

        return this;
    }

    public CommandBuilder setPermission(String permission) {
        this.data.setPermission(permission);

        return this;
    }

    public CommandBuilder setPermissionMessage(String message) {
        this.data.setPermissionMessage(message);

        return this;
    }

    public CommandBuilder setSubCommands(CommandData... subCommands) {
        this.data.setSubCommands(Arrays.asList(subCommands));

        return this;
    }
    public CommandBuilder addSubCommands(CommandData... subCommands) {
        this.data.addSubCommands(Arrays.asList(subCommands));

        return this;
    }
    public CommandBuilder addSubCommand(CommandData subCommand) {
        this.data.addSubCommands(Arrays.asList(subCommand));

        return this;
    }
    public CommandBuilder addSubCommand(CommandData subCommand, int index) {
        List<CommandData> subCommands = data.getSubcommands();

        subCommands.add(index, subCommand);

        data.setSubCommands(subCommands);

        return this;
    }

    public CommandBuilder setSubCommands(SubCommandBuilder... subCommands) {
        this.data.setSubCommands(
                Arrays.asList((CommandData[]) Arrays.stream(subCommands).map(SubCommandBuilder::getData).toArray())
        );

        return this;
    }
    public CommandBuilder addSubCommands(SubCommandBuilder... subCommands) {
        this.data.addSubCommands(
                Arrays.asList((CommandData[]) Arrays.stream(subCommands).map((command) -> command.getData()).toArray())
        );

        return this;
    }
    public CommandBuilder addSubCommand(SubCommandBuilder subCommand) {
        this.data.addSubCommands(Arrays.asList(subCommand.getData()));

        return this;
    }
    public CommandBuilder addSubCommand(SubCommandBuilder subCommand, int index) {
        List<CommandData> subCommands = data.getSubcommands();

        subCommands.add(index, subCommand.getData());

        data.setSubCommands(subCommands);

        return this;
    }

    public CommandBuilder setParameters(Parameter... params) {
        this.data.setParameters(Arrays.asList(params));

        return this;
    }
    public CommandBuilder addParameters(Parameter... params) {
        this.data.addParameters(Arrays.asList(params));

        return this;
    }
    public CommandBuilder addParameter(Parameter param) {
        this.data.addParameters(Arrays.asList(param));

        return this;
    }

    public boolean checkArguments(CommandSender sender, String[] arguments) {
        return CommandBuilder.checkArguments(sender, this.data.getParameters().toArray(new Parameter[0]), arguments);
    }
    public boolean checkArgumentValue(CommandSender sender, String[] arguments) {
        return CommandBuilder.checkArgumentValues(sender, this.data.getParameters().toArray(new Parameter[0]), arguments);
    }
    public boolean checkArgumentLength(CommandSender sender, String[] arguments) {
        return CommandBuilder.checkArgumentLength(sender, this.data.getParameters().toArray(new Parameter[0]), arguments);
    }

    public Command register() {
        Command command = this.build();

        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            field.setAccessible(true);

            CommandMap map = (CommandMap) field.get(Bukkit.getServer());

            map.register(command.getName(), command);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            Bukkit.getLogger().log(Level.SEVERE, "[BukkitUtils/CommandBuilder] Could not register command '" + command.getName() + "'!");
        }

        return command;
    }

    public Command build() {
        return CommandBuilder.build(this);
    }

    public static boolean checkArguments(CommandSender sender, Parameter[] parameters, String[] arguments) {
        if (!CommandBuilder.checkArgumentLength(sender, parameters, arguments)) return false;

        return CommandBuilder.checkArgumentValues(sender, parameters, arguments);
    }
    public static boolean checkArgumentValues(CommandSender sender, Parameter[] parameters, String[] arguments) {
        if (!CommandBuilder.checkArgumentLength(sender, parameters, arguments)) return false;

        for (int index = 0; index < arguments.length; index++) {
            Parameter parameter = parameters[index];
            String argument = arguments[index];

            if (!parameter.getChecker().test(argument)) {
                sender.sendMessage(parameter.errorMessage);

                return false;
            }

            if (!parameter.checkValue(argument)) return false;
        }

        return true;
    }
    public static boolean checkArgumentLength(CommandSender sender, Parameter[] parameters, String[] arguments) {
        int requiredParams = 0;
        Parameter param = parameters[0];
        int index = 1;

        while(param.getRequired()) {
            requiredParams++;

            param = parameters[index];

            index++;
        }

        if (arguments.length >= requiredParams) {
            return true;
        }

        sender.sendMessage(Creator.text("Missing Arguments! (" + arguments.length + "/" + requiredParams + ")", ChatColor.DARK_RED));

        return false;
    }

    public static Command build(CommandBuilder builder) {
        int index = 0;
        for (CommandData subCommand : builder.getData().getSubcommands()) {
            Parameter parameter;

            if(builder.getData().getParameters().size() - 1 >= index)
                parameter = builder.getData().getParameters().get(index);
            else {
                parameter = new Parameter().setTypes(ParameterType.Custom).require();
            }

            List<Parameter> parameters = builder.data.getParameters();
            parameters.remove(parameter);

            List<String> customStrings = new ArrayList<>(Arrays.asList(parameter.getCustomStrings()));
            customStrings.add(subCommand.getCommand());
            parameter.setCustomStrings(customStrings.toArray(parameter.getCustomStrings()));

            parameters.add(parameter);
            
            if(subCommand.getParameters() != null) {
                parameters.addAll(subCommand.getParameters());
            }

            builder.setParameters(parameters.toArray(new Parameter[0]));

            index++;
        }

        CommandData data = builder.data;

        Command command = new Command(data.getCommand()) {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                if(!builder.checkArguments(sender, args)) {
                    sender.sendMessage(Creator.text("Could not execute command!", ChatColor.DARK_RED));

                    return false;
                }

                for (CommandData subCommand : builder.getData().getSubcommands()) {
                    if(args.length >= 1) {
                        if(Objects.equals(subCommand.getCommand(), args[0])) {
                            boolean executeCommand = true;

                            if(subCommand.getPermission() == null) {
                                if(!sender.hasPermission(subCommand.getPermission())) {
                                    executeCommand = false;

                                    if(subCommand.getPermissionMessage() == null) {
                                        sender.sendMessage(Creator.text(subCommand.getPermissionMessage(), ChatColor.DARK_RED));
                                    }
                                    else {
                                        sender.sendMessage(Creator.text("You do not have the required permissions to execute this command!", ChatColor.DARK_RED));
                                    }
                                }
                            }

                            if(executeCommand) {
                                List<String> argsList = new ArrayList<>(Arrays.asList(args));
                                argsList.remove(0);

                                return subCommand.getExecutor().apply(sender, argsList.toArray(args));
                            }

                            return false;
                        }
                    }
                }

                return data.getExecutor().apply(sender, args);
            }

            @Override
            public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                for (int index = 0; index < data.getParameters().size(); index++)
                    if(index == args.length - 1)
                        return data.getParameters().get(index).getTabList();

                return new ArrayList<>();
            }
        };

        command.setAliases(data.getAliases());
        command.setDescription(data.getDescription());
        command.setPermission(data.getPermission());
        command.setPermissionMessage(data.getPermissionMessage());

        return command;
    }
}
