package boxWorld;


public class View {

	final static int ORIGIN_X = 60;
	final static int ORIGIN_Y = 100;
	final static int CELL_SIZE = 45;
	private Graphics _graphics;

	public void setGraphics(final Graphics graphics) {
		_graphics = graphics;
	}

	public void draw(Cell[][] fieldMatrix) {
		drawField(fieldMatrix);

	}

	private void drawField(Cell[][] fieldMatrix) {
		int colorIndex;
		FieldCells fs;
		for (int i = 0; i < fieldMatrix.length; i++) {
			for (int j = 0; j < fieldMatrix[i].length; j++) {
				if(fieldMatrix[i][j].isVisible()){
					fs = fieldMatrix[i][j].convertCellToFieldCells();
					colorIndex = fieldMatrix[i][j].getColorIndex(fs);
					drawCell(colorIndex, j, i);		
				}
			}
		}

	}

	protected void drawCell(int colorIndex, int row, int col) {
		_graphics.fillRect(ORIGIN_X + col * CELL_SIZE, ORIGIN_Y + row
				* CELL_SIZE, CELL_SIZE, CELL_SIZE, colorIndex);

	}

	public void clear() {
		_graphics.clearField();	
	}

	public void showMessage() {
		_graphics.showMessage();
	}

}
