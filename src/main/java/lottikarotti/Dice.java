package lottikarotti;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dice {

	private final List<String> DICE_RESULT = Arrays.asList("1", "2", "3", "kraken", "1 or planc", "1 or bridge",
			"planc", "bridge");

	public List<String> getDiceResults() {
		return Collections.unmodifiableList(DICE_RESULT);
	}

	@SuppressWarnings("resource")
	public String roleTheDice() {
		String diceResult;
		Integer decision;

		diceResult = DICE_RESULT.get(rngInteger(1, 5));
		if (diceResult.equals("2")) {
			return DICE_RESULT.get(1);
		}
		if (diceResult.equals("3")) {
			return DICE_RESULT.get(2);
		}
		if (diceResult.equals("kraken")) {
			return DICE_RESULT.get(3);
		}
		if (diceResult.equals("1 or planc")) {
			System.out.println("1(1) or planc(2)?");
			decision = new Scanner(System.in).nextInt();
			if (decision.equals(1)) {
				return DICE_RESULT.get(0);
			}
			if (decision.equals(2)) {
				return DICE_RESULT.get(6);
			}
		}
		if (diceResult.equals("1 or bridge")) {
			System.out.println("1(1) or bridge(2)?");
			decision = new Scanner(System.in).nextInt();
			if (decision.equals(1)) {
				return DICE_RESULT.get(0);
			}
			if (decision.equals(2)) {
				return DICE_RESULT.get(7);
			}
		}
		return null;
	}

	public void actionOrMove(String diceResult) {

		if (diceResult.equals(DICE_RESULT.get(0))) {
			Integer steps = Integer.parseInt(DICE_RESULT.get(0));
			move(steps);
			
		}
		if (diceResult.equals(DICE_RESULT.get(1))) {
			Integer steps = Integer.parseInt(DICE_RESULT.get(1));
			move(steps);
		}
		if (diceResult.equals(DICE_RESULT.get(2))) {
			Integer steps = Integer.parseInt(DICE_RESULT.get(2));
			move(steps);
		}
		if (diceResult.equals(DICE_RESULT.get(3))) {
			karken();
		}
		if (diceResult.equals(DICE_RESULT.get(6))) {
			planc();
		}
		if (diceResult.equals(DICE_RESULT.get(7))) {
			bridge();
		}
	}
	
	private static void move(Integer steps) {
		System.out.println("move " + steps);		
	}

	private void bridge() {
		System.out.println("bridge");
	}

	private void planc() {
		System.out.println("planc");

	}

	private void karken() {
		System.out.println("kraken");

	}

	private static Integer rngInteger(Integer min, Integer max) {
		Random rand = new Random();
		return rand.nextInt(max - min) + min;
	}
}
