package com.example.owner.gameproject;

import android.content.Context;
import android.os.Vibrator;


/**
 * @see android.os.Vibrator
 *
 * @author Varun Sriram
 * @version 1.2
 * @since 2015-01-23
 *
 */

public class Vibrate
{

    /**
     * VIBRATETIME long value representing the length of the
     *             vibrator time.
     *
     */

    private static final long VIBRATETIME = 250;


    /**
     * vibrator Vibrator instance representing the Android Vibrator.
     *
     */

    private static Vibrator vibrator;


    /**
     * context Context instance representing the context of the app.
     *
     */

    private static Context context;


    /**
     * Initializes the vibrator using context.VIBRATOR_SERVICE
     *
     */

    public static void initializeVibrate()
    {
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
    }


    /**
     * Setter for the context.
     *
     * @param context Context instance representing the context of the app.
     *
     */

    public static void setContext(Context context)
    {
        Vibrate.context = context;
    }


    /**
     * Calls the vibrate feature of the Android Vibrator.
     *
     */

    public static void vibrate()
    {
        vibrator.vibrate(VIBRATETIME);
    }

}
