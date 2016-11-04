package lottikarotti.setupservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SetupService {
	public static final Integer NUMBER_OF_FIELDS_ON_BOARD = 27;
	public final Integer NUMBER_OF_RABBITS_PER_PLAYER = 2;

	//@SuppressWarnings("resource")
	public Integer getNumberOfPlayers() {
		Integer numberOfPlayers;

		//System.out.println("Number of Players? (max 3)");
		//numberOfPlayers = new Scanner(System.in).nextInt();
		
		System.out.println("Number of Players? 3");
		numberOfPlayers = 3;
		
		return numberOfPlayers;
	}

	public List<Player> getPlayersByNumPlayers(Integer numberOfPlayers) {
		List<Player> players = new ArrayList<Player>();
		List<String> playerColors = Arrays.asList("red", "green", "blue");
		List<String> rabbitNames = Arrays.asList("rabbit 1", "rabbit 2");

		for (int i = 0; i < numberOfPlayers; i++) {
			Player newPlayer = new Player();
			newPlayer.setColor(playerColors.get(i));
			for (int j = 0; j < NUMBER_OF_RABBITS_PER_PLAYER; j++) {
				Rabbit newRabbit = new Rabbit();
				newRabbit.setColor(newPlayer.getColor());
				newRabbit.setName(rabbitNames.get(j));
				newPlayer.addRabbit(newRabbit);
			}
			players.add(newPlayer);
		}
		return players;
	}

	public List<Integer> getPlancsPosition() {
		List<Integer> holesPositions = new ArrayList<Integer>();
		List<Integer> plancsPositions = new ArrayList<Integer>();
		holesPositions.add(5);
		holesPositions.add(9);
		holesPositions.add(13);
		holesPositions.add(20);
		holesPositions.add(24);

		Integer firstPlanc = 0;
		Integer secondPlanc = 0;

		boolean setPlancs = true;
		while (setPlancs) {
			firstPlanc = holesPositions.get(rngInteger(0, holesPositions.size()));
			secondPlanc = holesPositions.get(rngInteger(0, holesPositions.size()));
			if (!firstPlanc.equals(secondPlanc)) {
				plancsPositions.add(firstPlanc);
				plancsPositions.add(secondPlanc);
				setPlancs = false;
			}
		}
		return plancsPositions;
	}

	public Board getBoard(Integer numberOfPlayers, List<Player> players, List<Integer> plancsPositions) {
		Board playBoard = new Board();
		for (int i = 0; i < NUMBER_OF_FIELDS_ON_BOARD; i++) {
			BoardField boardField = new BoardField();
			if (i == 5 || i == 9 || i == 13 || i == 20 || i == 24) {
				boardField.setHole(true);
			}
			if (i == plancsPositions.get(0) || i == plancsPositions.get(1)) {
				boardField.setPlank(true);
			}
			playBoard.setFields(boardField);
		}
		playBoard.setBridge(rngBoolean());

		playBoard.setPlayers(players);
		return playBoard;
	}

	private static boolean rngBoolean() {
		Random rand = new Random();
		return rand.nextBoolean();
	}

	private static Integer rngInteger(Integer min, Integer max) {
		Random rand = new Random();
		return rand.nextInt(max - min) + min;
	}
}
