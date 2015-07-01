package boxWorld;

import java.util.ArrayList;
import java.util.List;

public class Model {
	
	private IFileReader _reader;
	
	public Model(IFileReader reader) {
		_reader = reader;
	}
	

	Logic _logic = new Logic();
	private int level = 1;

		public Cell[][] getFieldMatrix() {
		return _logic.getFieldMatrix();
	}

	List<ModelListener> _listeners = new ArrayList<ModelListener>();

	public void addListener(final ModelListener listener) {
		_listeners.add(listener);
	}

	public void removeListener(final ModelListener listener) {
		_listeners.remove(listener);
	}

	void fireChangedEvent() {
		for (ModelListener modelListener : _listeners) {
			modelListener.onChange();
		}
	}
	
	void fireFinishLevel(){
		for (ModelListener modelListener : _listeners) {
			modelListener.finish();
		}
	}

	public void moveLeft() {
		if (_logic.getFieldMatrix() != null && _logic.move(Directions.Left)) {
			fireChangedEvent();
			if(_logic.isAllBoxFinish()){
				fireFinishLevel();
			}
		}
	}

	public void moveRight() {
		if (_logic.getFieldMatrix() != null && _logic.move(Directions.Right)) {
			fireChangedEvent();
			if(_logic.isAllBoxFinish()){
				fireFinishLevel();
			}
		}
	}

	public void moveDown() {
		if (_logic.getFieldMatrix() != null && _logic.move(Directions.Down)) {
			fireChangedEvent();
			if(_logic.isAllBoxFinish()){
				fireFinishLevel();
			}
		}
	}

	public void moveUp() {
		if (_logic.getFieldMatrix() != null && _logic.move(Directions.Up)) {
			fireChangedEvent();
			if(_logic.isAllBoxFinish()){
				fireFinishLevel();
			}
		}
	}

	public void undo() {
		if (_logic.getFieldMatrix() != null) {
			_logic.undo();
			fireChangedEvent();
		}
	}

	public void setUp() {
		_logic.init(level, _reader);
		fireChangedEvent();
	}

	public void restart() {
		_logic.clearState();
		setUp();
	}

	public void goToNextLevel() {
		if (level < Field.FINISH_LEVEL) {
			level++;
			_logic.clearState();
			_logic.resetIsAllBoxFinish();
			setUp();
		}
	}

	public int getLevel() {
		return level;
	}
}
