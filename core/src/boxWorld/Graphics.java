package boxWorld;

import java.awt.Color;

public interface Graphics {

	void fillRect(int i, int j, int width, int height, int color);
	
	void clearField();

	void showMessage();

}
