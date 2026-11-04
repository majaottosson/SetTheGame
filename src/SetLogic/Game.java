package SetLogic;

public class Game {
	public Player player;
	public Deck deck;
	public Board board;
	
	public Game(Player player) {
		this.player = player;
		this.deck = new Deck();
		this.board = new Board(deck);
	}
	
	public void restart() {
		player.setPoint(0);
		deck.filldeck();
		board = new Board(deck);
	}
}
