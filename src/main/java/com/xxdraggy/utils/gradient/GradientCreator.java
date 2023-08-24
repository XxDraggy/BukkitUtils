package com.xxdraggy.utils.gradient;

import com.xxdraggy.utils.gradient.exceptions.HomogeneousRainbowException;
import com.xxdraggy.utils.gradient.exceptions.InvalidColourException;
import com.xxdraggy.utils.gradient.exceptions.NumberRangeException;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;

public class GradientCreator {
    public static TextComponent generateGradient(String text, String[] spectrum){
        return GradientCreator.generateGradient(text, new TextComponent(), spectrum);
    }
    public static TextComponent generateGradient(String text, TextComponent component, String[] spectrum){
        int count = text.length();

        if (Math.min(count, spectrum.length) < 2) {
            return component;
        }

        ArrayList<ChatColor> colors = GradientCreator.getGradientColors(count, spectrum);

        for (int i = 0; i < colors.size(); i++) {
            TextComponent newComponent = new TextComponent();

            newComponent.setColor(colors.get(i));

            newComponent.setText(text.split("")[i]);

            component.addExtra(newComponent);
        }

        return component;
    }

    public static ArrayList<ChatColor> getGradientColors(int count, String[] spectrum) {
        Rainbow rainbow = new Rainbow();

        try {
            rainbow.setNumberRange(1, count);
            rainbow.setSpectrum(spectrum);
        } catch (HomogeneousRainbowException | InvalidColourException | NumberRangeException ignored) {} catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<ChatColor> colors = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            colors.add(ChatColor.of("#" + rainbow.colourAt(i)));
        }

        return colors;
    }
}
