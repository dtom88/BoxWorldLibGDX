package boxWorld;

public class Controller implements ModelListener {

	private View _view;
	private Model _model;

	@Override
	public void onChange() {
		_view.draw(_model.getFieldMatrix());
	}

	@Override
	public void finish() {
		_view.showMessage();
		onChange();
	}

	public void setView(final View view) {
		_view = view;
	}

	public void setModel(final Model model) {
		_model = model;
	}

	public void moveRight() {
		_model.moveRight();
	}

	public void moveLeft() {
		_model.moveLeft();
	}

	public void moveDown() {
		_model.moveDown();
	}

	public void moveUp() {
		_model.moveUp();
	}

	public void undo() {
		_model.undo();
	}

	public void setUp() {
		_model.setUp();

	}

	public void goToNextLevel() {
		_model.goToNextLevel();

	}

	public void restart() {
		_model.restart();
	}

}
