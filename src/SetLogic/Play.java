package SetLogic;

import Swing.PlaySwing;

public class Play {

	public static void main(String[] args) {
		
		Player player = new Player();
		Game game = new Game(player);
		
		PlaySwing play = new PlaySwing(game);
		play.run();
	}

}
