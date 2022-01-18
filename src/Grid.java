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
			move_up();
			break;	
			
		case 1:
			move_down();
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
		int l = 0,c = 0;
		//d�placement vers la droite	
		for(int x = 0;x < size;x++) // lignes
		{
			for(int y = 0; y < size; y++) // colonnes
			{
				if(direction == 2)
				{
					l = size-1-x;
					c = size-1-y;
				}
				if(direction == 3)
				{
				 l = x;
				 c = y;
				}

				
				if(blocTab[c][l] != -1) // si la case contient un bloc
				{
					// on va lire toutes les colonnes qui pr�c�dent ce bloc, et si on d�tecte du vide ou un bloc semblable, on effectue un d�calage
					
					int idx = c; // on m�morise la colonne � laquelle on se trouve
					
					if(direction == 2)
					{
					while(idx <= size-1 &&  (blocTab[idx][l] == -1 || blocTab[idx][l] == blocTab[c][l]))
					{idx ++;}
					idx -=1;
					}
					if(direction == 3)
					{
					while(idx >= 0 &&  (blocTab[idx][l] == -1 || blocTab[idx][l] == blocTab[c][l]))
					{idx --;}
					idx+=1;
					}
					if(c != idx)// si on a au moins boug� d'une case
					{
						if(blocTab[idx][l] == blocTab[c][l]) // si le dernier bloc a la m�me valeur que le bloc actuel
						{
							blocTab[idx][l] *= 2; // on double la valeur de la case du fond
							blocTab[c][l] = -1;//on vide l'ancienne case du bloc
						}
						else
						{
							blocTab[idx][l] = blocTab[c][l];//on d�place le bloc actuel
							blocTab[c][l] = -1;//on vide l'ancienne case du bloc
						}
					}
					
				}
				
			}
		}		
	}
	
	public void move_up_down(int direction)
	{
		int l = 0,c = 0;
		
		//d�placement vers le haut	
		for(int x = 0;l < size;x++) // lignes
		{
			for(int y = 1; y < size; y++) // colonnes 
			{
				if(direction == 1)
				{
					l = size-1-x;
					c = size-1-y;
				}
				if(direction == 0)
				{
				 l = x;
				 c = y;
				}
				
				if(blocTab[l][c] != -1) // si la case contient un bloc
				{
					// on va lire toutes les colonnes qui pr�c�dent ce bloc, et si on d�tecte du vide ou un bloc semblable, on effectue un d�calage
					
					int idx = c; // on m�morise la colonne � laquelle on se trouve
					
					if(direction == 0)
					{
					while(idx >= 0 &&  (blocTab[l][idx] == -1 || blocTab[l][idx] == blocTab[l][c]))
					{idx --;}
					idx+=1;
					}
					if(direction == 1)
					{
						while(idx <= size-1 &&  (blocTab[l][idx] == -1 || blocTab[l][idx] == blocTab[l][c]))
						{idx ++;}
						idx -=1;
					}
					
					
					if(c != idx)// si on a au moins boug� d'une case
					{
						if(blocTab[l][idx] == blocTab[l][c]) // si le dernier bloc a la m�me valeur que le bloc actuel
						{
							blocTab[l][idx] *= 2; // on double la valeur de la case du fond
							blocTab[l][c] = -1;//on vide l'ancienne case du bloc
						}
						else
						{
							blocTab[l][idx] = blocTab[l][c];//on d�place le bloc actuel
							blocTab[l][c] = -1;//on vide l'ancienne case du bloc
						}
					}
					
				}
				
			}
		}
	}
	
	public void move_up()
	{
		//d�placement vers le haut	
		for(int l = 0;l < size;l++) // lignes
		{
			for(int c = 1; c < size; c++) // colonnes 
			{
				
				if(blocTab[l][c] != -1) // si la case contient un bloc
				{
					// on va lire toutes les colonnes qui pr�c�dent ce bloc, et si on d�tecte du vide ou un bloc semblable, on effectue un d�calage
					
					int idx = c; // on m�morise la colonne � laquelle on se trouve
					
					while(idx >= 0 &&  (blocTab[l][idx] == -1 || blocTab[l][idx] == blocTab[l][c]))
					{idx --;}
					
					if(c != idx+1)// si on a au moins boug� d'une case
					{
						if(blocTab[l][idx+1] == blocTab[l][c]) // si le dernier bloc a la m�me valeur que le bloc actuel
						{
							blocTab[l][idx+1] *= 2; // on double la valeur de la case du fond
							blocTab[l][c] = -1;//on vide l'ancienne case du bloc
						}
						else
						{
							blocTab[l][idx+1] = blocTab[l][c];//on d�place le bloc actuel
							blocTab[l][c] = -1;//on vide l'ancienne case du bloc
						}
					}
					
				}
				
			}
		}
	}
	
	
	
	
	public void move_down()
	{
		for(int l = size-1;l >= 0;l--) // lignes
		{
			for(int c = size-1; c >= 0; c--) 
			{
				
				if(blocTab[l][c] != -1) // si la case contient un bloc
				{
					// on va lire toutes les colonnes qui pr�c�dent ce bloc, et si on d�tecte du vide ou un bloc semblable, on effectue un d�calage
					
					int idx = c; // on m�morise la colonne � laquelle on se trouve
					
					while(idx <= size-1 &&  (blocTab[l][idx] == -1 || blocTab[l][idx] == blocTab[l][c]))
					{idx ++;}
										
					if(c != idx-1)// si on a au moins boug� d'une case
					{
						if(blocTab[l][idx-1] == blocTab[l][c]) // si le dernier bloc a la m�me valeur que le bloc actuel
						{
							blocTab[l][idx-1] *= 2; // on double la valeur de la case du fond
							blocTab[l][c] = -1;//on vide l'ancienne case du bloc
						}
						else
						{
							blocTab[l][idx-1] = blocTab[l][c];//on d�place le bloc actuel
							blocTab[l][c] = -1;//on vide l'ancienne case du bloc
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
