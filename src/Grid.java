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
		
		int previous_bloc = -1; // bloc précédent
		
		System.out.println("Move "+direction);

			
			
		for(int l = 0;l < size;l++) // lignes
		{
			for(int c = 0; c < size; c++) // colonnes
			{
				
				if(blocTab[l][c] == -1) // si la case est vide
				{
					// on décale tout ce qui précède le vide
					move_line(l,c,0);
				}
				else
				{
				if(blocTab[l][c] == previous_bloc) // si on a le même bloc que le précédent
				{
					//on additionne les deux derniers blocs et on décale la ligne
				}
				else
				{
					// on attend
				}
				
				}
				
				previous_bloc = blocTab[l][c];
			}
		}
		
	}
	
	public void move_line(int line, int index, int direction)//décale la ligne à partir de index
	{
		if(index >0) // si on n'est pas dans la première case du tableau
		{
			int[] tab = new int[index];
			
			for(int i = 0; i < index;i++)// on charge les cases à décaler dans un tableau
			{
				tab[i] = blocTab[line][i];
			}
			
			for(int i = 0; i < index;i++)
			{
				blocTab[line][i+1] = tab[i];
			}
		}
		
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
		return false;
	}
	
	public boolean replay()
	{
		return false;
	}
	
}
