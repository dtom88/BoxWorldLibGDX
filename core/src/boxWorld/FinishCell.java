package boxWorld;

public class FinishCell extends Cell {
	
	@Override
	public boolean isFinish() {
		return true;
	}

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public Cell createCopy() {
		Cell result = new FinishCell();
		copy(result);
		return result;
	}

}
