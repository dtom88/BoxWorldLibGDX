package boxWorld;

public class InvisibleCell extends WallCell {

	 @Override
	 public boolean isVisible() {
	 return false;
	 }

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public boolean isFinish() {
		return false;
	}

	@Override
	public Cell createCopy() {
		Cell result = new InvisibleCell();
		copy(result);
		return result;
	}

}
