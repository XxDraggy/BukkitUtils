package com.xxdraggy.utils.gradient;

import com.xxdraggy.utils.gradient.exceptions.InvalidColourException;
import com.xxdraggy.utils.gradient.exceptions.NumberRangeException;

class ColorGradient {
    private int[] startColour = {0xff, 0x00, 0x00};
    private int[] endColour = {0x00, 0x00, 0xff};
    private double minNum = 0;
    private double maxNum = 100;

    public String colourAt(double number) {
        return 	formatHex(calcHex(number, startColour[0], endColour[0]))
                +	formatHex(calcHex(number, startColour[1], endColour[1]))
                +	formatHex(calcHex(number, startColour[2], endColour[2]));
    }

    private int calcHex(double number, int channelStart, int channelEnd) {
        double num = number;
        if (num < minNum) {
            num = minNum;
        }
        if (num > maxNum) {
            num = maxNum;
        }
        double numRange = maxNum - minNum;
        double cPerUnit = (channelEnd - channelStart)/numRange;
        return (int) Math.round(cPerUnit * (num - minNum) + channelStart);
    }

    private String formatHex (int val)
    {
        String hex = Integer.toHexString(val);
        if (hex.length() == 1) {
            return '0' + hex;
        } else {
            return hex;
        }
    }

    public void setNumberRange(double minNumber, double maxNumber) throws NumberRangeException {
        if (maxNumber > minNumber) {
            minNum = minNumber;
            maxNum = maxNumber;
        } else {
            throw new NumberRangeException(minNumber, maxNumber);
        }
    }

    public void setGradient(String colourStart, String colourEnd) throws InvalidColourException {
        startColour = getHexColour(colourStart);
        endColour = getHexColour(colourEnd);
    }

    private int[] getHexColour(String s) throws InvalidColourException {
        if (s.matches("^#?[0-9a-fA-F]{6}$")){
            return rgbStringToArray(s.replace("#", ""));
        } else {
            throw new InvalidColourException(s);
        }
    }

    private int[] rgbStringToArray(String s){
        int red = Integer.parseInt(s.substring(0,2), 16);
        int green = Integer.parseInt(s.substring(2,4), 16);
        int blue = Integer.parseInt(s.substring(4,6), 16);
        return new int[]{red, green, blue};
    }

}
