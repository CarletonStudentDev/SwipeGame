package com.example.owner.gameproject;

/**
 * Multiplier class representing the score
 * multiplier for the game. If the user gets
 * a certain amount of correct match, it will
 * increment the multiplier value. Otherwise
 * reset it to zero.
 *
 * @author Varun Sriram
 * @version 1.2
 * @since 2014-11-27
 *
 */
public class Multiplier
{
    /**
     * MAXIMUM integer value representing the maximum correct matches
     * the Player has to get before the multiplier has been
     * incremented.
     *
     * MAX_MULTIPLIER integer value representing the maximum value the
     * multiplier will achieve
     *
     * MULTIPLIER_SCALIER integer value representing the scalier of the
     * multiplier
     *
     * DEFAULTMULTIPLIER integer value representing the default multiplier
     * value which the multiplier will be set to at
     * beginning of game and each time there is a lose
     * of life/incorrect match
     *
     * DEFAULTMETERCOUNT integer value representing the default meter count
     * of the multiplier which the meter will be set to
     * at beginning of game and each time the multiplier is
     * reset or multiplier is incremented
     *
     */

    private static final int MAXIMUM = 5,
                             MAX_MULTIPLIER = 16,
                             MULTIPLIER_SCALIER = 2,
                             DEFAULTMULTIPLIER = 1,
                             DEFAULTMETERCOUNT = 0;


    /**
     * currentMultiplier integer representing the current
     * multiplier the meter will show.
     *
     * meterCount integer representing the number of correct
     * matches the player made.
     *
     */

    private int currentMultiplier,
                meterCount;


    /**
     * Constructor for the Multiplier class.
     *
     */

    public Multiplier()
    {
        this.currentMultiplier = DEFAULTMULTIPLIER;
        this.meterCount = DEFAULTMETERCOUNT;
    }


    /**
     * Getter method for the current multiplier.
     *
     * @return integer representing the current score multiplier.
     *
     */

    public int getMultiplier()
    {
        return this.currentMultiplier;
    }


    /**
     * Getter for the meter count.
     *
     * @return meterCount integer value representing the current
     *                    meter count.
     *
     */

    public int getMultiplierBarNum()
    {
        return this.meterCount;
    }


    /**
     * Increments the multiplier meter.
     *
     */

    private void increment()
    {
        meterCount++;

        // check if it is at threshold
        if(meterCount >= MAXIMUM)
        {
            //if the multiplier is not at maximum
            if(currentMultiplier < MAX_MULTIPLIER)
            {
                //scale the score multiplier and reset the meter.
                this.currentMultiplier *= MULTIPLIER_SCALIER;
                this.meterCount = DEFAULTMETERCOUNT;
            }

            else if(currentMultiplier >= MAX_MULTIPLIER && meterCount >= MAXIMUM)
                meterCount = MAXIMUM;
            

        }
    }


    /**
     * Clears the meter
     *
     */

    private void clear()
    {
        this.currentMultiplier = DEFAULTMULTIPLIER;
        this.meterCount = DEFAULTMETERCOUNT;
    }


    /**
     * Increments the meter count and multiplier number
     * if a correct match has been made.
     *
     */

    public void correctMatch()
    {
        this.increment();
    }


    /**
     * Clears the multiplier and meter count
     * if an incorrect match has been made.
     *
     */

    public void incorrectMatch()
    {
        this.clear();
    }

}
