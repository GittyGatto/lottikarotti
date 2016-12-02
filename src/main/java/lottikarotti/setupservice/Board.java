package lottikarotti.setupservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

	/*
	 * create PlayBoard
	 * 
	 * board fields 27 holes 5 9 13 20 24 shortcuts 15-18 23-26
	 * 
	 * 1 or planc 1 or bridge 2, 3, maulwurf
	 */

	private List<Player> players = new ArrayList<Player>();
	private List<BoardField> fields = new ArrayList<BoardField>();
	private boolean bridge;

	public boolean isBridge() {
		return bridge;
	}

	public void setBridge(boolean bridge) {
		this.bridge = bridge;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<BoardField> getField() {
		return Collections.unmodifiableList(fields);
	}

	public void setFields(BoardField fields) {
		this.fields.add(fields);
	}
	
	public void showPlayers(Board board){
		List<String> playerInfo = new ArrayList<String>();
		System.out.println("~~~~~~~~~~~~~~~~~~~");

		for (int i = 0; i < players.size(); i++) {
			playerInfo.add("player " + players.get(i).getColor());
			System.out.println(playerInfo.get(i));
		}
	}

	public void showBoard(Board board) {
		List<String> rabbitInfo = new ArrayList<String>();
		List<String> fieldInfo = new ArrayList<String>();
		List<String> bridgeInfo = new ArrayList<String>();
		System.out.println("~~~~~~~~~~~~~~~~~~~");
		
		
		
		for (int i = 0; i < board.getField().size(); i++) {
			if (board.getField().get(i).getRabbit()!=null){
				rabbitInfo.add(board.getField().get(i).getRabbit().getName()+" ");
			} else {
				rabbitInfo.add("   ");
			}
		}

		for (int i = 0; i < board.getField().size(); i++) {

			if (board.getField().get(i).isHole()) {
				fieldInfo.add("o  ");
			} else {
				fieldInfo.add("x  ");
			}
			if (i == 14 && board.isBridge()) {
				bridgeInfo.add("b  ");
			} else if (i == 22 && !board.isBridge()) {
				bridgeInfo.add("b  ");
			} else {
				bridgeInfo.add("   ");
			}
		}

		StringBuilder rabbitString = new StringBuilder();
		for (String s : rabbitInfo) {
			rabbitString.append(s);
		}
		StringBuilder fieldString = new StringBuilder();
		for (String s : fieldInfo) {
			fieldString.append(s);
		}
		StringBuilder bridgeString = new StringBuilder();
		for (String s : bridgeInfo) {
			bridgeString.append(s);
		}
		System.out.println(rabbitString);
		System.out.println(fieldString);
		System.out.println(bridgeString);
	}

	@Override
	public String toString() {
		return "Board(bridge=" + bridge + ", players=" + players + ", fields=" + fields + ")";
	}
}
