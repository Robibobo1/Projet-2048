//import java.lang.Math;
import java.util.Random;

import hevs.graphics.FunGraphics;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Grid {
	int size;
	int blocTab[][];
	
	Grid(int size)
	{
		this.size = size;
		blocTab = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				blocTab[j][i] = -1;
			}
		}
		
	}
	
	public void moveBlocs(int direction)
	{
		
	}
	
	public void createBloc()
	{
		Random r = new Random();
		int randomX;
		int randomY;
		
		do {
			randomX = r.nextInt(size);
			randomY = r.nextInt(size);
		} while (blocTab[randomX][randomY] != -1);
		
		blocTab[randomX][randomY] = 2;
	}
	
	public String drawBloc(int posX, int posY)
	{
		return "";
	}
	
	public String drawGrid()
	{
		return "";
	}
	
	public boolean is2048()
	{
		return false;
	}
	
}
