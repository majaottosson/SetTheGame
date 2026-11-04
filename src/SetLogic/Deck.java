package SetLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck{
	public List<Cards> deck;
	
	public Deck() {
		this.deck = new ArrayList<Cards>();
		
		String[] colors = {"green", "blue", "red"};
		Character[] figures = {'<', 'O', 'S'};
		String[] filled = {"empty", "striped", "full"};
		
		for (String c: colors){
			for (Character fig: figures) {
				for(String fil: filled) {
					for (int i = 1; i < 4; i++) {
						deck.add(new Cards(c, i, fig, fil));
					}
				}
			}
		}
	}
	
	public void shuffle() {
	    Collections.shuffle(deck);
	}

	
	public List<Cards> getDeck() {
		return deck;
	}
	
	public void filldeck() {
		deck = new Deck().getDeck();
	}
}


