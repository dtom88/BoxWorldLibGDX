package boxWorld;


public abstract class Cell {

	private boolean _isObstacle = false;
	private boolean _hasPusher = false;
	private boolean _hasBox = false;
	private boolean _isVisible = true;
	private boolean _isFinish = false;

	public abstract boolean isObstacle();

	public boolean hasPusher() {
		return _hasPusher;
	}

	public void setHasPusher(boolean hasPusher) {
		_hasPusher = hasPusher;
	}

	public boolean hasBox() {
		return _hasBox;
	}

	public void setHasBox(boolean hasBox) {
		_hasBox = hasBox;
	}

	public abstract boolean isVisible();

	public abstract boolean isFinish();

	public FieldCells convertCellToFieldCells() {
		if (isFinish() && hasBox()) {
			return FieldCells.FinishBox;
		}
		if (!isVisible()) {
			return FieldCells.InvisibleCell;
		}
		if (isFinish() && !hasPusher()) {
			return FieldCells.FinishCell;
		}
		if (hasPusher()) {
			return FieldCells.PusherCell;
		}
		if (hasBox()) {
			return FieldCells.BoxCell;
		}
		if (!isObstacle() && !isFinish() && !hasBox() && !hasPusher()
				&& isVisible()) {
			return FieldCells.CorridorCell;
		}
		if (isObstacle()) {
			return FieldCells.WallCell;
		}
		if (!isVisible()) {
			return FieldCells.InvisibleCell;
		}
		return null;
	}

	public int getColorIndex(FieldCells fCells) {
		FieldCells fs = fCells;
		int colorIndex = 8;
		switch (fs) {
		case BoxCell:
			colorIndex = 3;
			break;
		case FinishCell:
			colorIndex = 4;
			break;
		case PusherCell:
			colorIndex = 6;
			break;
		case WallCell:
			colorIndex = 0;
			break;
		case CorridorCell:
			colorIndex = 1;
			break;
		case FinishBox:
			colorIndex = 5;
			break;

		}
		return colorIndex;
	}

	public abstract Cell createCopy();

	protected void copy(Cell cell) {
		cell._hasBox = this._hasBox;
		cell._hasPusher = this._hasPusher;
		cell._isFinish = this._isFinish;
		cell._isObstacle = this._isObstacle;
		cell._isVisible = this._isVisible;

	}
}
