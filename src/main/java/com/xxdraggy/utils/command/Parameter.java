package com.xxdraggy.utils.command;

import com.xxdraggy.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Parameter {
    private String name;
    public Parameter setName(String name) {
        this.name = name;

        return this;
    }
    public Parameter appendName(String toAppend) {
        this.name += toAppend;

        return this;
    }
    public String getName() {
        return name;
    }

    private ParameterType[] types = new ParameterType[] { ParameterType.Custom };
    public Parameter setTypes(ParameterType... types) {
        this.types = types;

        return this;
    }
    public ParameterType[] getTypes() {
        return types;
    }

    private String[] customStrings = new String[] {};
    public Parameter setCustomStrings(String... customStrings) {
        this.customStrings = customStrings;

        return this;
    }
    public String[] getCustomStrings() {
        return customStrings;
    }

    private boolean isRequired;
    public Parameter require() {
        this.isRequired = true;

        return this;
    }
    public Parameter setRequired(boolean isRequired) {
        this.isRequired = isRequired;

        return this;
    }
    public boolean getRequired() {
        return isRequired;
    }

    public String errorMessage = "";
    public Predicate<String> checker = (string) -> true;
    public Parameter setChecker(String errorMessage, Predicate<String> checker) {
        this.checker = checker;

        return this;
    }
    public Predicate<String> getChecker() {
        return checker;
    }

    public boolean checkValue(String value) {
        for (ParameterType type : this.getTypes()) {
            switch (type) {
                case OnlinePlayer:
                    return Utils.getOnlinePlayer(value) != null;
                case OfflinePlayer:
                    return Utils.getOfflinePlayer(value) != null;
                case Player:
                    return Utils.playerExists(value);
                case WhitelistedPlayer:
                    return Utils.getWhitelistedPlayer(value) != null;
                case Custom:
                    boolean exists = false;

                    for (String custom : this.getCustomStrings()) {
                        if (Objects.equals(custom, value)) {
                            exists = true;

                            break;
                        }
                    }

                    return exists;
            }
        }

        return true;
    }

    public List<String> getTabList() {
        ParameterType[] types = this.getTypes();
        List<String> results = Arrays.asList(this.getCustomStrings());

        for (ParameterType type : types) {
            if(type == ParameterType.OnlinePlayer) {
                for (Player player : Bukkit.getOnlinePlayers())
                    results.add(player.getName());
            }
            else if(type == ParameterType.OfflinePlayer) {
                for (OfflinePlayer player : Bukkit.getOfflinePlayers())
                    if(Bukkit.getPlayer(player.getUniqueId()) == null) results.add(player.getName());
            }
            else if(type == ParameterType.Player) {
                for (OfflinePlayer player : Bukkit.getOfflinePlayers())
                    results.add(player.getName());
            }
            else if(type == ParameterType.WhitelistedPlayer) {
                for (OfflinePlayer player : Bukkit.getWhitelistedPlayers())
                    results.add(player.getName());
            }
        }

        return results;
    }

    /*
    private String onp = "OnlinePlayer";
    private String ofp = "OfflinePlayer";
    private String jp = "JoinedPlayer";
    private String wp = "WhitelistedPlayer";
    public Parameter setPlayerNames(String onp, String ofp, String jp, String wp) {
        this.onp = onp;
        this.ofp = ofp;
        this.jp = jp;
        this.wp = wp;

        return this;
    }

    public List<String> getNames() {
        List<String> results = Arrays.asList(this.getCustomStrings());

        for (ParameterType type : this.getTypes()) {
            if(type == ParameterType.OnlinePlayers) {
                results.add(this.onp);
            }
            else if(type == ParameterType.OfflinePlayers) {
                results.add(this.ofp);
            }
            else if(type == ParameterType.JoinedPlayers) {
                results.add(this.jp);
            }
            else if(type == ParameterType.WhitelistedPlayers) {
                results.add(this.wp);
            }
        }

        return results;
    }

    public String getNamesString() {
        StringBuilder result = new StringBuilder();

        boolean first = true;
        for (String customString : this.customStrings) {
            if(!first) {
                result.append("/");
            }

            result.append(customString);

            first = false;
        }

        for (ParameterType type : this.getTypes()) {
            result.append("/");

            if(type == ParameterType.OnlinePlayers) {
                result.append(this.onp);
            }
            else if(type == ParameterType.OfflinePlayers) {
                result.append(this.ofp);
            }
            else if(type == ParameterType.JoinedPlayers) {
                result.append(this.jp);
            }
            else if(type == ParameterType.WhitelistedPlayers) {
                result.append(this.wp);
            }
        }

        return result.toString();
    }
    */
}
