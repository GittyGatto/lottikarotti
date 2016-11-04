package lottikarotti;

import java.util.ArrayList;
import java.util.List;

import lottikarotti.setupservice.Board;
import lottikarotti.setupservice.Player;

public class GameController {

	public void startGame(Board board, List<Player> players) {
		boolean totalVictory = false;
		Dice dice = new Dice();

		while (totalVictory == false) {

			for (Player player : players) {
				String result = dice.roleTheDice();
				dice.actionOrMove(result);
				System.out.println("player "+ player.getColor() + " dice result: " + result);
				System.out.println();
			}
		}
	}
}
