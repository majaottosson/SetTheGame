package SetLogic;

import java.util.List;

public class Board {
	List<Cards> deck;
	Cards[][] board;
	
	public Board(Deck deck) {
		this.deck = deck.getDeck();
		this.board = new Cards[4][3];
	}
	
	public Cards[][] getBoard(){
		return board;
	}
	
	public void FillBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = deck.remove(0);
			}
		}
	}
	
	public void removeCards(Cards one, Cards two, Cards three) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				Cards card = board[i][j];
				if (card != null && (card.equals(one) || card.equals(two) || card.equals(three))) {
					board [i][j] = null; 
				}
			}
		}
	}
	
	public void RefillBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == null && 0 < deck.size()) {
					board [i][j] = deck.remove(0); 
				}
			}
		}
	}
}
