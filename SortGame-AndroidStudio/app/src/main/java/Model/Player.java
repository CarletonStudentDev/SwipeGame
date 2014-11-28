package Model;

/**
 * Created by jeton on 27/11/14.
 * @author Jeton
 * @author Varun
 * @version 1.0
 * @since 2014-11-27
 */
public class Player implements Listener
{
    private int lives;
    private long score;
    public Player(){
        this.lives = 3;
        this.score = 0;
    }
    public int getLives() {
        return lives;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }
    public long getScore() {
        return score;
    }
    public void setScore(long score) {
        this.score = score;
    }
    @Override
    public void correctMatch(GameEvent e) {
// TODO Auto-generated method stub
        Game g = (Game)e.getSource();
        Multiplier m = (Multiplier) g.getMultiplierMeter();

        this.score += 100 * m.getMultiplier();
    }
    @Override
    public void incorrectMatch(GameEvent e) {
// TODO Auto-generated method stub
        this.lives--;
    }
    @Override
    public void timeOut(GameEvent e) {
// TODO Auto-generated method stub
    }
    @Override
    public void livesFinish(GameEvent e) {
// TODO Auto-generated method stub
    }
    @Override
    public void roundsOver(GameEvent e) {
// TODO Auto-generated method stub
    }
}
