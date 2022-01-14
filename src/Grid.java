//import java.lang.Math;
import java.util.Random;
import hevs.graphics.FunGraphics;

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
	
	private String drawBloc(int posX, int posY)
	{
		String out = "";
		out += blocTab[posX][posY];
		return out;
	}
	
	public String drawGrid()
	{
		String out = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				out += (blocTab[j][i] == -1) ? " " + drawBloc(j,i)  : "  " + drawBloc(j,i) ;
			}
			out += '\n';
		}
		return out;
	}
	
	public boolean is2048()
	{
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(blocTab[j][i] == 2048) return true;
			}
		}
		return false;
	}	
	
}
