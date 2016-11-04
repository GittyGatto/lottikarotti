package lottikarotti.gamecontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lottikarotti.setupservice.Board;
import lottikarotti.setupservice.BoardField;
import lottikarotti.setupservice.Player;
import lottikarotti.setupservice.Rabbit;

public class GameController {

	public void startGame(Board board) {

		List<Player> players = new ArrayList<Player>();
		players = playersToList(board);

		boolean totalVictory = false;
		while (totalVictory == false) {

			Dice dice = new Dice();
			for (Player player : players) {
				String result = dice.roleTheDice();
				dice.actionOrMove(board, player, result);
			}
		}
	}

	private List<Player> playersToList(Board board) {
		List<Player> players = new ArrayList<Player>();

		for (int i = 0; i < board.getPlayers().size(); i++) {
			players.add(board.getPlayers().get(i));
		}
		return players;
	}

	@SuppressWarnings("resource")
	public static void move(Board board, Player player, Integer steps) {
		System.out.println("Dice result: Player " + player.getColor() + " move " + steps);

		Boolean rabbitOnField = atLeastOnePlayerRabbitOnBoard(board, player);
		
		Rabbit one = player.getRabbits().get(0);
		Rabbit two = player.getRabbits().get(1);
		
		if (!rabbitOnField){
			moveRabbit(board, one);
		} else {
			System.out.println("Select a Rabbit to move (1 or 2): ");
			Integer decision = new Scanner(System.in).nextInt();
			if (decision==1){
				moveRabbit(board, one);
			} else {
				moveRabbit(board, two);
			}
		}
		
		
		
		
		
		//Boolean fieldPassable = checkIfFieldIsPassable(board, rabbit);

		

	}

	private static void moveRabbit(Board board, Rabbit rabbit) {

		//get position
		Integer rabbitPosition = null;
		for (int i = 0; i < board.getField().size() && rabbitPosition !=null; i++) {
		}
		
	}

	private static Boolean checkIfFieldIsPassable(Board board) {
		
		return null;
	}

	private static Boolean atLeastOnePlayerRabbitOnBoard(Board board, Player player) {
		Boolean result = false;
		for (int i = 0; result == true && i < board.getField().size(); i++) {

			if (board.getField().get(i).getRabbit().getColor() == player.getColor()) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}

	public static void bridge(Board board, Player player) {
		System.out.println("Dice result: Player " + player.getColor() + " bridge");
	}

	public static void planc(Board board, Player player) {
		System.out.println("Dice result: Player " + player.getColor() + " planc");
	}

	public static void karken(Board board, Player player) {
		System.out.println("Dice result: Player " + player.getColor() + " kraken");
	}

}
