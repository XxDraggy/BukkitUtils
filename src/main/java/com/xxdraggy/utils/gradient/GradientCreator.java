package com.xxdraggy.utils.gradient;

import com.xxdraggy.utils.gradient.exceptions.HomogeneousRainbowException;
import com.xxdraggy.utils.gradient.exceptions.InvalidColourException;
import com.xxdraggy.utils.gradient.exceptions.NumberRangeException;
import com.xxdraggy.utils.creator.text.TextCreator;

import java.util.ArrayList;

public class GradientCreator {
    public static String generateGradient(String text, String[] colours){
        int count = text.length();

        if (Math.min(count, colours.length) < 2) {
            return text;
        }

        ArrayList<String> cols = GradientCreator.getGradientColors(count, colours);

        StringBuilder colourCodes = new StringBuilder();

        for (int i = 0; i < cols.size(); i++) {
            String color = TextCreator.hexColor(cols.get(i));

            colourCodes.append(color).append(text.charAt(i));
        }

        return colourCodes.toString();
    }

    public static ArrayList<String> getGradientColors(int count, String[] colours) {
        Rainbow rainbow = new Rainbow();

        try {
            rainbow.setNumberRange(1, count);
            rainbow.setSpectrum(colours);
        } catch (HomogeneousRainbowException | InvalidColourException | NumberRangeException ignored) {} catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> hexCodes = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            hexCodes.add(rainbow.colourAt(i));
        }

        return hexCodes;
    }
}
