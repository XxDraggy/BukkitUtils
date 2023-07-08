package com.xxdraggy.utils.gradient;

import com.xxdraggy.utils.gradient.exceptions.HomogeneousRainbowException;
import com.xxdraggy.utils.gradient.exceptions.InvalidColourException;
import com.xxdraggy.utils.gradient.exceptions.NumberRangeException;

import java.util.ArrayList;

/**
 * The Rainbow class by default maps the range 0 to 100 (inclusive) to the colours of the rainbow
 * (i.e., a gradient transitioning from red to yellow to lime to blue)
 * @author Sophiah (Zing-Ming)
 */
public class Rainbow {

    private double minNum;
    private double maxNum;
    private String[] colours;
    private ArrayList<ColorGradient> colorGradients;

    /**
     * Constructor. By default, the number range is from 0 to 100, and the spectrum is a rainbow.
     */
    public Rainbow() {
        try {
            minNum = 0;
            maxNum = 100;
            colours = new String[] {
                "red",
                "yellow",
                "lime",
                "blue"
            };

            setSpectrum(colours);
        }
        catch (HomogeneousRainbowException | InvalidColourException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Returns the hex colour corresponding to the number. If number is out of range,
     * it returns the appropriate hex colour corresponding to either the minNumber or maxNumber.
     * @param number The number for which you want to find the corresponding colour
     * @return The corresponding colour represented as a HTML RGB hexidecimal String
     */
    public String colourAt(double number) {
        if (colorGradients.size() == 1) {
            return colorGradients.get(0).colourAt(number);
        } else {
            int index = (int) Math.min(
                Math.floor(
                    (
                        Math.max(
                            number,
                            minNum
                        )
                        -
                        minNum
                    )
                    /
                    (
                        maxNum
                        -
                        minNum
                    )
                    /
                    colorGradients.size()
                ),
                colorGradients.size() - 1
            );

            return colorGradients.get(index).colourAt(number);
        }
    }

    /**
     * Sets the spectrum of the Rainbow object. By default, the spectrum is a rainbow.
     * You must have a minimum of two colours, but you can specify more than two colours.
     * Colours can be in the form "red", "ff0000", or "#ff0000".
     * For example, <code>rainbow.setSpectrum("red", "yellow", "white");</code>
     * makes the "Rainbow" a colour gradient from red to yellow to white.
     * @param spectrum Two or more Strings representing HTML colours,
     * or pass in a whole String array of length 2 or greater
     * @throws HomogeneousRainbowException if there is less than two arguments
     * @throws InvalidColourException if one of the arguments is an invalid colour
     */
    public void setSpectrum (String ... spectrum) throws HomogeneousRainbowException, InvalidColourException {
        try {
            if (spectrum.length < 2) {
                throw new HomogeneousRainbowException();
            } else {
                double increment = (maxNum - minNum)/(spectrum.length - 1);
                ColorGradient firstGradient = new ColorGradient();
                firstGradient.setGradient(spectrum[0], spectrum[1]);
                firstGradient.setNumberRange(minNum, minNum + increment);

                colorGradients = new ArrayList<ColorGradient>();
                colorGradients.add(firstGradient);

                for (int i = 1; i < spectrum.length - 1; i++) {
                    ColorGradient colourGradient = new ColorGradient();
                    colourGradient.setGradient(spectrum[i], spectrum[i + 1]);
                    colourGradient.setNumberRange(minNum + increment * i, minNum + increment * (i + 1));
                    colorGradients.add(colourGradient);
                }

                colours = spectrum;
            }
        }
        // This exception is theoretically impossible, so rethrow as unchecked exception
        catch (NumberRangeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the number range of the Rainbow object. By default, it is 0 to 100.
     * @param minNumber The minimum number of the number range
     * @param maxNumber The maximum number of the number range
     * @throws NumberRangeException if minNumber is greater than maxNumber
     */
    public void setNumberRange(double minNumber, double maxNumber) throws NumberRangeException
    {
        try {
            if (maxNumber > minNumber) {
                minNum = minNumber;
                maxNum = maxNumber;
                setSpectrum(colours);
            } else {
                throw new NumberRangeException(minNumber, maxNumber);
            }
        }
        // These exceptions are theoretically impossible, so rethrow as unchecked exceptions
        catch (HomogeneousRainbowException e) {
            throw new RuntimeException(e);
        } catch (InvalidColourException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Same as colourAt(double number)
     */
    public String colorAt(double number) {
        return colourAt(number);
    }

}