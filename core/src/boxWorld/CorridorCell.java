package boxWorld;

public class CorridorCell extends Cell {

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public boolean isFinish() {
		return false;
	}

	@Override
	public Cell createCopy() {
		Cell result = new CorridorCell();
		copy(result);
		return result;
	}

}
