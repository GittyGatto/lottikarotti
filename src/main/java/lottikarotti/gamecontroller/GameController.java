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
				System.out.println("Player " + player.getColor() + " - It\'s your turn.");
				String result = dice.roleTheDice();
				dice.actionOrMove(board, player, result);
			}
		}
	}

	@SuppressWarnings("resource")
	public static void move(Board board, Player player, Integer steps) {

		System.out.println("Player " + player.getColor() + " - Dice result: move " + steps);

		// select a rabbit to move
		// and a field to start
		Rabbit currentRabbit = null;
		BoardField currentField = null;
		Integer currentPosition = null;
		List<BoardField> allRabbitsOnFields = getRabbitPositions(board);
		List<BoardField> allPlayerRabbitsOnField = getRabbitPositionsforPlayer(board, player);
		List<BoardField> allBoardFields = getAllBoardFields(board);
		List<Rabbit> playerRabbits = new ArrayList<Rabbit>();

		if (!atLeastOnePlayerRabbitOnBoard(board, player)) {
			System.out.println("Player " + player.getColor() + " - You have no rabbit on field.");
			currentRabbit = player.getRabbits().get(0);
			currentField = null;
			currentPosition = null;
			System.out.println("Player " + player.getColor() + " - Your current rabbit is: " + currentRabbit.getName()
					+ " Position: " + currentPosition);
		}
		if (allPlayerRabbitsOnField.size() == 1) {
			currentRabbit = allPlayerRabbitsOnField.get(0).getRabbit();
			currentField = allPlayerRabbitsOnField.get(0);
			currentPosition = getRabbitPosition(currentRabbit, allBoardFields);
			System.out.println(
					"Player " + player.getColor() + " - You have one rabbit on field. Position: " + currentPosition);
		}
		if (allPlayerRabbitsOnField.size() == 2) {
			System.out.println("Player " + player.getColor() + " - You have two rabbits on field "
					+ allPlayerRabbitsOnField.get(0) + " and " + allPlayerRabbitsOnField.get(1));
			System.out.println("Select first (1) or second (2) rabbit for your turn.");
			Integer decision = new Scanner(System.in).nextInt();
			if (decision == 1) {
				currentRabbit = allPlayerRabbitsOnField.get(0).getRabbit();
				currentField = allPlayerRabbitsOnField.get(0);
				currentPosition = getRabbitPosition(currentRabbit, allBoardFields);
			} else {
				currentRabbit = allPlayerRabbitsOnField.get(1).getRabbit();
				currentField = allPlayerRabbitsOnField.get(1);
				currentPosition = getRabbitPosition(currentRabbit, allBoardFields);
			}
		}

		// set rabbits which not on board yet
		if (currentPosition == null) {
			boolean positionFound = false;
			for (int i = 0; i < allBoardFields.size() && positionFound == false; i++)
				if (fieldIsFree(allBoardFields.get(i)) && fieldHasHoleWithPlanc(allBoardFields.get(i))) {
					allBoardFields.get(i).setRabbit(currentRabbit);
					currentField = allBoardFields.get(i);
					currentPosition = i;
					positionFound = true;
				}
		}

		// move dat shit
		for (int i = 0; i < steps; i++) {
			if (fieldIsFree(allBoardFields.get(currentPosition + 1)) == true
					&& fieldHasHoleWithPlanc(allBoardFields.get(currentPosition + 1)) == true) {
				allBoardFields.get(currentPosition).setRabbit(null);
				allBoardFields.get(currentPosition + 1).setRabbit(currentRabbit);
				currentPosition = getRabbitPosition(currentRabbit, allBoardFields);
			} else {
//				allBoardFields.get(currentPosition).setRabbit(null);
//				currentPosition = getRabbitPosition(currentRabbit, allBoardFields)+1;
			}

			if (i == steps - 1) {

			}
		}
		System.out.println("Player " + player.getColor() + " - Rabbit " + currentRabbit.getColor() + " new position: "
				+ currentPosition);
		board.showBoard(board);
	}

	public static void bridge(Board board, Player player) {
		System.out.println("Player " + player.getColor() + " - Dice result:  bridge");
	}

	public static void planc(Board board, Player player) {
		System.out.println("Player " + player.getColor() + " - Dice result:  planc");
	}

	public static void karken(Board board, Player player) {
		System.out.println("Player " + player.getColor() + " - Dice result:  kraken");
	}

	private List<Player> playersToList(Board board) {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < board.getPlayers().size(); i++) {
			players.add(board.getPlayers().get(i));
		}
		return players;
	}

	private static Boolean atLeastOnePlayerRabbitOnBoard(Board board, Player player) {

		List<BoardField> allPlayerRabbitsOnField = getRabbitPositionsforPlayer(board, player);

		if (allPlayerRabbitsOnField.size() == 0) {
			return false;
		}
		return true;
	}

	private static List<BoardField> getRabbitPositions(Board board) {
		List<BoardField> result = new ArrayList<BoardField>();
		for (int i = 0; i < board.getField().size(); i++) {
			if (board.getField().get(i).getRabbit() != null) {
				result.add(board.getField().get(i));
			}
		}
		return result;
	}

	private static List<BoardField> getRabbitPositionsforPlayer(Board board, Player player) {
		List<BoardField> result = new ArrayList<BoardField>();
		for (int i = 0; i < board.getField().size(); i++) {
			if (board.getField().get(i).getRabbit() == player.getRabbits().get(0)
					|| board.getField().get(i).getRabbit() == player.getRabbits().get(1)) {
				result.add(board.getField().get(i));
			}
		}
		return result;
	}

	private static List<BoardField> getAllBoardFields(Board board) {
		List<BoardField> result = new ArrayList<BoardField>();
		for (int i = 0; i < board.getField().size(); i++) {
			result.add(board.getField().get(i));
		}
		return result;
	}

	private static boolean fieldIsFree(BoardField field) {
		Boolean result;
		if (field.getRabbit() != null) {
			result = false;
		} else if (field.isHole() == true && field.isPlank() == false) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	private static boolean fieldHasHoleWithPlanc(BoardField field) {
		Boolean result;
		if (field.isHole() == true && field.isPlank() == false) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	private static int getRabbitPosition(Rabbit currentRabbit, List<BoardField> allBoardFields) {
		int result = 0;
		for (int i = 0; i < allBoardFields.size(); i++) {
			if (currentRabbit.equals(allBoardFields.get(i).getRabbit())) {
				result = i;
			}
		}
		return result;
	}
}