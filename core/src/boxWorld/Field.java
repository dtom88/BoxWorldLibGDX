package boxWorld;

import java.util.ArrayList;

public class Field {

	public static final int FINISH_LEVEL = 10;
	final static int PUSHER_INDEX = '4';
	final static int BOX_INDEX = '3';
	final static int INVIBLE_INDEX = '0';
	final static int FINISH_INDEX = '5';
	final static int FINISH_BOX_INDEX = '6';
	final static int CORRIDOR_INDEX = '2';
	final static int WALL_INDEX = '1';
	private int _width;
	private int _height;
	private ArrayList<Cell> finishList = new ArrayList<Cell>();
	int _col;
	int _row;

	Cell[][] _field;
	
	public ArrayList<Cell> getFinishList(){
		return finishList;
	}
	
	private Cell convertFromChar(char c) {
		Cell result = null;
		switch (c) {
		case INVIBLE_INDEX:
			result = new InvisibleCell();
			break;
		case WALL_INDEX:
			result = new WallCell();
			break;
		case CORRIDOR_INDEX:
			result = new CorridorCell();
			break;
		case BOX_INDEX:
			result = new CorridorCell();
			result.setHasBox(true);
			break;
		case PUSHER_INDEX:
			result = new CorridorCell();
			result.setHasPusher(true);
			break;
		case FINISH_INDEX:
			result = new FinishCell();
			break;
		case FINISH_BOX_INDEX:
			result = new FinishCell();
			result.setHasBox(true);
		}
		return result;
	}

	public void setup(ArrayList<String> strs) {
		String s;
		_height = strs.size();
		_width = strs.get(1).length();
	//	finishList.clear();
		_field = new Cell[_height][_width];
		for (int i = 0; i < strs.size(); i++) {
			s = strs.get(i);
			for (int j = 0; j < s.length(); j++) {
				_field[i][j] = convertFromChar(s.charAt(j));
				if (s.charAt(j) == PUSHER_INDEX) {
					_col = i;
					_row = j;
				}
				if(s.charAt(j) == FINISH_INDEX){
					finishList.add(_field[i][j]);
				}
			}
		}
	}

	public void clearFinishList() {
		finishList.clear();	
	}

}
