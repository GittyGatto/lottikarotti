package lottikarotti.setupservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
	private List<Rabbit> rabbits = new ArrayList<Rabbit>();
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public List<Rabbit> getRabbits() {
		return Collections.unmodifiableList(rabbits);
	}

	public void addRabbit(Rabbit newRabbit) {
		this.rabbits.add(newRabbit);
	}

	@Override
	public String toString() {
		return "Player(color=" + color + ", rabbits=" + rabbits + ")";
	}
}
