package SetLogic;



public class Rules {
	boolean isSet;
	
	public Rules() {
		this.isSet = false;
	}
	
	public boolean getisSet() {
		return isSet;
	}
	
	//They all have the same number or have three different numbers.
    //They all have the same shape or have three different shapes.
    //They all have the same shading or have three different shadings.
    //They all have the same color or have three different colors.
	public boolean AllRules(Cards one, Cards two, Cards three) {
		Cards[] cards = {one, two, three};
		
		if (ColorRule(cards) == -1 || FigureRule(cards) == -1 || FilledRule(cards) == -1 || NumberRule(cards) == -1) {
			return isSet = false;
		} else {
			return isSet = true;
		}	
	}
	
	// returns 1 if all the colors are the same, 0 if all the colors are different, -1 if two colors are the same
	public int ColorRule(Cards[] cards){
		if (cards[0].getColor().equals(cards[1].getColor()) 
				&& cards[0].getColor().equals(cards[2].getColor())
				&& cards[1].getColor().equals(cards[2].getColor())) {
			return 1;
			
		} else if (!cards[0].getColor().equals(cards[1].getColor()) 
				&& !cards[0].getColor().equals(cards[2].getColor())
				&& !cards[1].getColor().equals(cards[2].getColor())
				) {
			return 0;
			
		} else {
			return -1;
		}
	}
	
	//returns 1 if all the figures are the same, 0 if all figures are different, -1 if two are the same
	public int FigureRule(Cards[] cards){
		if (cards[0].getFigure() == cards[1].getFigure()
				&& cards[0].getFigure() == cards[2].getFigure()
				&& cards[1].getFigure() == cards[2].getFigure()) {
			return 1;
			
		} else if (cards[0].getFigure() != cards[1].getFigure()
				&& cards[0].getFigure() != cards[2].getFigure()
				&& cards[1].getFigure() != cards[2].getFigure()) {
			return 0;
		
		} else return -1;
	}
	
	//returns 1 if all fillings are the same, 0 if all fillings are different, -1 if two are the same
	public int FilledRule(Cards[] cards){
		if (cards[0].getFilled().equals(cards[1].getFilled()) 
				&& cards[0].getFilled().equals(cards[2].getFilled())
				&& cards[1].getFilled().equals(cards[2].getFilled())) {
			return 1;
			
		} else if (!cards[0].getFilled().equals(cards[1].getFilled()) 
				&& !cards[0].getFilled().equals(cards[2].getFilled())
				&& !cards[1].getFilled().equals(cards[2].getFilled())
				) {
			return 0;
			
		} else return -1;
		
	}
	
	//return 1 if all numbers are the same, 0 if all numbers are different, -1 if two are the same
	public int NumberRule(Cards[] cards){
		if (cards[0].getNumber() == cards[1].getNumber() 
				&& cards[0].getNumber() == cards[2].getNumber()
				&& cards[1].getNumber() == cards[2].getNumber()) {
			return 1;
			
		} else if (cards[0].getNumber() != cards[1].getNumber() 
				&& cards[0].getNumber() != cards[2].getNumber()
				&& cards[1].getNumber() != cards[2].getNumber()) {
			return 0;
			
		} else return -1;
	}
}
	
