package com.example.owner.gameproject;

import android.content.res.Resources;

/**
 * Loads the colors when required by the app using the application resources.
 *
 * @see android.content.res.Resources
 *
 * @author Jeton Sinoimeri
 * @version 1.1
 * @since 2015-03-19
 *
 */

public class ColorsLoader
{
    /**
     * resources Resources instance representing the resources of the app.
     *
     */

    private static Resources resources;


    /**
     * Setter for the resources of the app.
     *
     * @param resources Resources instance representing the resources of the app.
     */

    public static void setResources(Resources resources)
    {
        ColorsLoader.resources = resources;
    }


    /**
     * Loads a color from resources given an integer.
     *
     * @param colorId integer value representing the id of the color.
     * @return the loaded resource color corresponding to the colorId, otherwise
     *         0 if the color does not exist.
     */

    public static int loadColorByInt(int colorId)
    {
        if (colorId == 1)
            return resources.getColor(R.color.red);

        else if (colorId == 2)
            return resources.getColor(R.color.green);

        else if (colorId == 3)
            return resources.getColor(R.color.blue);

        else if (colorId == 4)
            return resources.getColor(R.color.purple);

        else if (colorId == 5)
            return resources.getColor(R.color.white);

        else if (colorId == 6)
            return resources.getColor(R.color.lightGray);

        else if (colorId == 7)
            return resources.getColor(R.color.lightRed);

        else if (colorId == 8)
            return resources.getColor(R.color.mediumRed);

        else if (colorId == 9)
            return resources.getColor(R.color.darkRed);

        else if (colorId == 10)
            return resources.getColor(R.color.darkestRed);

        else if (colorId == 11)
            return resources.getColor(R.color.lightBlue);

        else if (colorId == 12)
            return resources.getColor(R.color.mediumBlue);

        else if (colorId == 13)
            return resources.getColor(R.color.darkBlue);

        else if (colorId == 14)
            return resources.getColor(R.color.darkestBlue);

        else if (colorId == 15)
            return resources.getColor(R.color.orange);

        else if (colorId == 16)
            return resources.getColor(R.color.gray);

        else if (colorId == 17)
            return resources.getColor(R.color.black);

        return 0;
    }


    /**
     * Loads a color from resources given the name.
     *
     * @param colorName String object representing the name of the color.
     * @return the loaded resource color corresponding to the color name, otherwise
     *         0 if the color does not exist.
     */

    public static int loadColorByName(String colorName)
    {
        colorName = colorName.toLowerCase();

        if (colorName.equals("red"))
            return loadColorByInt(1);

        else if (colorName.equals("green"))
            return loadColorByInt(2);

        else if (colorName.equals("blue"))
            return loadColorByInt(3);

        else if (colorName.equals("purple"))
            return loadColorByInt(4);

        else if (colorName.equals("white"))
            return loadColorByInt(5);

        else if (colorName.equals("lightgray") || colorName.equals("light gray"))
            return loadColorByInt(6);

        else if (colorName.equals("lightred") || colorName.equals("light red"))
            return loadColorByInt(7);

        else if (colorName.equals("mediumred") || colorName.equals("medium red"))
            return loadColorByInt(8);

        else if (colorName.equals("darkred") || colorName.equals("dark red"))
            return loadColorByInt(9);

        else if (colorName.equals("darkestred") || colorName.equals("darkest red"))
            return loadColorByInt(10);

        else if (colorName.equals("lightblue") || colorName.equals("light blue"))
            return loadColorByInt(11);

        else if (colorName.equals("mediumblue") || colorName.equals("medium blue"))
            return loadColorByInt(12);

        else if (colorName.equals("darkblue") || colorName.equals("dark blue"))
            return loadColorByInt(13);

        else if (colorName.equals("darkestblue") || colorName.equals("darkest blue"))
            return loadColorByInt(14);

        else if (colorName.equals("orange"))
            return loadColorByInt(15);

        else if (colorName.equals("gray"))
            return loadColorByInt(16);

        else if (colorName.equals("black"))
            return loadColorByInt(17);

        return 0;
    }
}
