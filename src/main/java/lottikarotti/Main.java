package lottikarotti;

import java.util.ArrayList;
import java.util.List;

import lottikarotti.setupservice.Board;
import lottikarotti.setupservice.Player;
import lottikarotti.setupservice.SetupService;

public class Main {
	public static void main(String[] args) {
		Integer numberOfPlayers;
		List<Player> players = new ArrayList<Player>();
		List<Integer> plancsPositions = new ArrayList<Integer>();
		Board board = new Board();

		SetupService setupService = new SetupService();
		numberOfPlayers = setupService.getNumberOfPlayers();
		players = setupService.getPlayersByNumPlayers(numberOfPlayers);
		plancsPositions = setupService.getPlancsPosition();
		board = setupService.getBoard(numberOfPlayers, players, plancsPositions);
		board.showBoard(board);

		
		GameController gameController = new GameController();
		gameController.startGame(board, players);
		
	}
}