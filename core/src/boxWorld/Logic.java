package boxWorld;

import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.Gdx;

public class Logic {

	private int col;
	private int row;

	private Stack<Cell[][]> listOfState = new Stack<Cell[][]>();
	private Field field = new Field();
	private Cell[][] fieldMatrix;
	private boolean _isAllBoxFinish = false;

	public void init(Integer level, IFileReader reader) {
		field.clearFinishList();
		field.setup(reader.read("field" + level.toString()));
		fieldMatrix = field._field;
		col = field._col;
		row = field._row;
		listOfState.push(copyOfMatrix(fieldMatrix));
	}

	public Cell[][] getFieldMatrix() {
		return fieldMatrix;
	}

	private Index chooseIndexes(Directions d) {
		Index index = new Index();
		switch (d) {
		case Left:
			index.nextCol = -1;
			index.nextNextCol = -2;
			break;
		case Right:
			index.nextCol = 1;
			index.nextNextCol = 2;
			break;
		case Up:
			index.nextNextRow = 2;
			index.nextRow = 1;
			break;
		case Down:
			index.nextNextRow = -2;
			index.nextRow = -1;
			break;
		}
		return index;
	}

	public boolean move(Directions d) {
		Index index = chooseIndexes(d);
		if (!fieldMatrix[col + index.nextCol][row + index.nextRow].isObstacle()) {
			moveBox(d);
			movePusher(d);
			return true;
		}
		return false;
	}

	private void moveBox(Directions d) {
		Index index = chooseIndexes(d);
		if (canMoveBox(index)) {
			ArrayList<Cell> fList = new ArrayList<Cell>();
			fieldMatrix[col + index.nextNextCol][row + index.nextNextRow]
					.setHasBox(true);
			fieldMatrix[col + index.nextCol][row + index.nextRow]
					.setHasBox(false);
			if (fieldMatrix[col + index.nextNextCol][row + index.nextNextRow]
					.isFinish()) {
				fList = field.getFinishList();
				boolean isFinished = true;
				for (Cell fCell : fList) {
					isFinished = isFinished && fCell.hasBox();
					if(!isFinished){break;}
				}
				_isAllBoxFinish = isFinished;
			}
		}
	}

	private void movePusher(Directions d) {
		Index index = chooseIndexes(d);
		if (canMovePusher(index)) {

			fieldMatrix[col][row].setHasPusher(false);
			fieldMatrix[col + index.nextCol][row + index.nextRow]
					.setHasPusher(true);
			col += index.nextCol;
			row += index.nextRow;
			listOfState.push(copyOfMatrix(fieldMatrix));

		}
	}

	private boolean canMoveBox(Index index) {
		return fieldMatrix[col + index.nextCol][row + index.nextRow].hasBox()
				&& !fieldMatrix[col + index.nextNextCol][row
						+ index.nextNextRow].hasBox()
				&& !fieldMatrix[col + index.nextNextCol][row
						+ index.nextNextRow].isObstacle();
	}

	private boolean canMovePusher(Index index) {
		return !fieldMatrix[col + index.nextCol][row + index.nextRow].hasBox();
	}


	public void undo() {
		if (listOfState.size() > 1) {
			listOfState.pop();
			fieldMatrix = copyOfMatrix(listOfState.peek());
			field.setup(convertCellToArrayOfString(fieldMatrix));
			col = field._col;
			row = field._row;
		}

	}

	private ArrayList<String> convertCellToArrayOfString(Cell[][] fMatrix) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < fMatrix.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < fMatrix[i].length; j++) {
				FieldCells fs = fMatrix[i][j].convertCellToFieldCells();
				switch (fs) {
				case BoxCell:
					sb.append('3');
					break;
				case FinishCell:
					sb.append('5');
					break;
				case PusherCell:
					sb.append('4');
					break;
				case WallCell:
					sb.append('1');
					break;
				case CorridorCell:
					sb.append('2');
					break;
				case FinishBox:
					sb.append('6');
					break;
				case InvisibleCell:
					sb.append('0');
				}

			}
			result.add(sb.toString());
		}
		return result;
	}

	private Cell[][] copyOfMatrix(Cell[][] matrix) {
		Cell[][] copyOfMatrix = new Cell[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				copyOfMatrix[i][j] = matrix[i][j].createCopy();
			}
		}
		return copyOfMatrix;
	}

	public void clearState() {
		listOfState.clear();
	}

	public boolean isAllBoxFinish() {
		return _isAllBoxFinish;
	}

	public void resetIsAllBoxFinish() {
		_isAllBoxFinish = false;
	}

}
