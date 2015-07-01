package boxWorld;

public class WallCell extends Cell {
	
	@Override
	public boolean isObstacle() {
		return true;
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
		Cell result = new WallCell();
		copy(result);
		return result;
	}

}
