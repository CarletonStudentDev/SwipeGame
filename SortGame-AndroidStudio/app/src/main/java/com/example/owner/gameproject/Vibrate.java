package com.example.owner.gameproject;

/**
 * Created by erickadbay on 15-03-20.
 */
import android.content.Context;
import android.os.Vibrator;


/**
 * @see android.os.Vibrator
 *
 * @author Varun Sriram
 * @version 1.1
 * @since 2015-01-23
 *
 */

public class Vibrate
{

    /**
     * VIBRATETIME: long value representing the length of the
     *              vibrator time.
     *
     */

    private static final long VIBRATETIME = 250;


    /**
     * vibrator: Vibrator instance representing the Android Vibrator.
     *
     */

    private static Vibrator vibrator;


    /**
     * Constructor for the Vibrate class.
     *
     * @param context: Context instance representing the Context
     *                 of the app.
     *
     */

    public Vibrate(Context context)
    {
        this.vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
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