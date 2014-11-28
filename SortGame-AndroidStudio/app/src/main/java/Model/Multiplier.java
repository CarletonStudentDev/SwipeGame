package Model;

/**
 * Model of the Multiplier Meter
 *
 * @author varunsriram
 *
 * @version 1.0
 * @since 2014-11-27
 */

public class Multiplier implements Listener {
    /**
     * MAXIMUM: no of correct matches needed to scale the multiplier
     * MAX_MULTIPLIER: Maxmimum no of the multiplier
     * MUTIPLIERSCALER: the amount the multiplier scale once the meter is at threshold.
     * currentMultiplier: the current multiplier the meter will show.
     * meterCount: the no of correct matches the player made.
     */
    private static int MAXIMUM = 5;
    private static int MAX_MULTIPLIER = 16;
    private static int MULTIPLIER_SCALER = 2;
    private int currentMultiplier;
    private int meterCount ;
    /**
     * Constructor of the multiplier class.
     */
    public Multiplier(){
        this.currentMultiplier = 1;
        this.meterCount = 0;
    }
    /**
     * Getter method to get the current multiplier.
     * @return the current score multiplier.
     */
    public int getMultiplier(){
        return this.currentMultiplier;
    }
    /**
     * Method to increment the multiplier meter appropriately.
     */
    public void increment(){
//increment the metercount
        meterCount++;
//check if it is at threshold
        if(meterCount>= MAXIMUM){
//if the multiplier is not at maximum
            if(currentMultiplier < MAX_MULTIPLIER){
//scale the score multiplier and reset the meter.
                this.currentMultiplier *= MULTIPLIER_SCALER;
                this.meterCount = 0;
            }
        }
    }
    /**
     * Method to clear the meter.
     */
    public void clear(){
        this.currentMultiplier = 1;
        this.meterCount = 0;
    }
    @Override
/**
 * Method to tell the meter what to do if the Game triggers a correct match Event.
 * @param e GameEvent Object that is passed in by Game class.
 */
    public void correctMatch(GameEvent e) {
// TODO Auto-generated method stub
        this.increment();
    }
    @Override
/**
 * Method to tell the meter what to do if the Game triggers a inccorrect match Event.
 * @param e GameEvent Object that is passed in by Game class.
 */
    public void incorrectMatch(GameEvent e) {
// TODO Auto-generated method stub
        this.clear();
    }
    @Override
/**
 * Method to tell the meter what to do if the Game triggers a Timeout Event.
 * @param e GameEvent Object that is passed in by Game class.
 */
    public void timeOut(GameEvent e) {
// TODO Auto-generated method stub
        this.clear();
    }
    @Override
/**
 * Method to tell the meter what to do if the Game triggers a lives finished Event.
 * @param e GameEvent Object that is passed in by Game class.
 */
    public void livesFinish(GameEvent e) {
// TODO Auto-generated method stub
        this.clear();
    }
    @Override
/**
 * Method to tell the meter what to do if the Game triggers a lives roundsOver Event.
 * @param e GameEvent Object that is passed in by Game class.
 */
    public void roundsOver(GameEvent e) {
// TODO Auto-generated method stub
    }
}
