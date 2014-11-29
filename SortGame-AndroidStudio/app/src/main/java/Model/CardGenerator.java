import java.util.Random;


public class CardGenerator {
	private Random r;
	private int numOfPatternAttr;
	public CardGenerator(){
		this.numOfPatternAttr = 4;
		r = new Random();
	}
	
	public CardGenerator(int numOfPatterns){
		this.numOfPatternAttr = numOfPatterns;
		r = new Random();
	}
	
	public Card generateCard(){
		return new Card(this.getRandValue(1, numOfPatterAttr+1));
		
	}
	
	public int getRandValue(int low, int high){
		
		int R = r.nextInt(high-low) + low;
		return R;
	}
	
	
}
