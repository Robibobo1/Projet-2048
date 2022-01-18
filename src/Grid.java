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
		System.out.println("Move "+direction);

		switch(direction)
		{
		case 0:
			move_up_down(0);
			break;	
			
		case 1:
			move_up_down(1);
			break;
			
		case 2:
			//move_right();
			move_left_right(2);
			break;
			
		case 3:
			move_left_right(3);
			break;
		}
		
		
	}
		
	
	public void move_left_right(int direction)
	{
		int line = 0,column = 0;
		
		// boucles permettant de scanner la grille
		for(int x = 0;x < size;x++) 
		{
			for(int y = 0; y < size; y++) 
			{
				// choix des valeurs de line et column selon sens
				if(direction == 2)
				{
					line = size-1-x;
					column = size-1-y;
				}
				if(direction == 3)
				{
				 line = x;
				 column = y;
				}

				
				if(blocTab[column][line] != -1) // si la case contient un bloc
				{
					
					
					int idx = column; // on mémorise la colonne où l'on est
					
					// Détection de la case où bouger selon sens
					if(direction == 2)
					{
					while(idx <= size-1 &&  (blocTab[idx][line] == -1 || blocTab[idx][line] == blocTab[column][line]))
					{idx ++;}
					idx -=1;
					}
					if(direction == 3)
					{
					while(idx >= 0 &&  (blocTab[idx][line] == -1 || blocTab[idx][line] == blocTab[column][line]))
					{idx --;}
					idx+=1;
					}
					
					
					if(column != idx)// si on peut bouger d'au moins une case
					{
						if(blocTab[idx][line] == blocTab[column][line]) // si on a un bloc de même valeur
						{
							blocTab[idx][line] *= 2; // on double la valeur de la case du fond
							blocTab[column][line] = -1;//on vide l'ancienne case du bloc
						}
						else // si la case est vide
						{
							blocTab[idx][line] = blocTab[column][line];
							blocTab[column][line] = -1;//on vide l'ancienne case du bloc
						}
					}
					
				}
				
			}
		}		
	}
	
	public void move_up_down(int direction)
	{
		int line = 0,column = 0;
		
		// boucles permettant de scanner la grille	
		for(int x = 0;x < size;x++)
		{
			for(int y = 0; y < size; y++) 
			{
				// choix des valeurs de line et column selon direction
				if(direction == 1)
				{
					line = size-1-x;
					column = size-1-y;
				}
				if(direction == 0)
				{
				 line = x;
				 column = y;
				}
				
				 if(blocTab[line][column] != -1) // si la case contient un bloc
				{
					
					
					int idx = column; // on mémorise la colonne où l'on est
					
					// Détection de la case où bouger selon direction
					if(direction == 0)
					{
					while(idx >= 0 &&  (blocTab[line][idx] == -1 || blocTab[line][idx] == blocTab[line][column]))
					{idx --;}
					idx+=1;
					}
					if(direction == 1)
					{
						while(idx <= size-1 &&  (blocTab[line][idx] == -1 || blocTab[line][idx] == blocTab[line][column]))
						{idx ++;}
						idx -=1;
					}
					
					
					if(column != idx)// si on peut au moins bouger d'une case
					{
						if(blocTab[line][idx] == blocTab[line][column]) // si la case contient un bloc de même valeur
						{
							blocTab[line][idx] *= 2; // on double la valeur de la case du fond
							blocTab[line][column] = -1;//on vide l'ancienne case du bloc
						}
						else // si la case est vide
						{
							blocTab[line][idx] = blocTab[line][column];//on dï¿½place le bloc actuel
							blocTab[line][column] = -1;//on vide l'ancienne case du bloc
						}
					}
					
				} 
				
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
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(blocTab[j][i] == 2048) return true;
			}
		}
		return false;
	}	
	
}
