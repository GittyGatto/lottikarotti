package lottikarotti.setupservice;

public class BoardField {
	private boolean hole;
	private boolean plank;
	private Rabbit rabbit;

	public boolean isHole() {
		return hole;
	}

	public void setHole(boolean hole) {
		this.hole = hole;
	}

	public boolean isPlank() {
		return plank;
	}

	public void setPlank(boolean plank) {
		this.plank = plank;
	}

	@Override
	public String toString() {
		return "BoardField(hole=" + hole + ", plank=" + plank + ", rabbit=" + getRabbit() + ")";
	}

	public Rabbit getRabbit() {
		return rabbit;
	}

	public void setRabbit(Rabbit rabbit) {
		this.rabbit = rabbit;
	}
	
	
}
