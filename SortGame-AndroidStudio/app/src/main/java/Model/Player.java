package com.example.swipegame;

public class Player implements GameEventListener {
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
		this.score += 100*(Game)e.getSource().(Multiplier)getMultiplierMeter().getMultiplier();
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
