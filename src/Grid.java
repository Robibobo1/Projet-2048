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
			move_right();
			break;
			
		case 3:
			move_left();
			break;
		}
		
		
	}
	
	
	public void move_left()
	{

		//déplacement vers la gauche	
		for(int l = 0;l < size;l++) // lignes
		{
			for(int c = 0; c < size; c++) // colonnes
			{
				
				if(blocTab[c][l] != -1) // si la case contient un bloc
				{
					// on va lire toutes les colonnes qui précèdent ce bloc, et si on détecte du vide ou un bloc semblable, on effectue un décalage
					
					int idx = c; // on mémorise la colonne à laquelle on se trouve
					
					while(idx >= 0 &&  (blocTab[idx][l] == -1 || blocTab[idx][l] == blocTab[c][l]))
					{idx --;}
										
					if(c != idx+1)// si on a au moins bougé d'une case
					{
						if(blocTab[idx+1][l] == blocTab[c][l]) // si le dernier bloc a la même valeur que le bloc actuel
						{
							blocTab[idx+1][l] *= 2; // on double la valeur de la case du fond
							blocTab[c][l] = -1;//on vide l'ancienne case du bloc
						}
						else
						{
							blocTab[idx+1][l] = blocTab[c][l];//on déplace le bloc actuel
							blocTab[c][l] = -1;//on vide l'ancienne case du bloc
						}
					}
					
				}
				
			}
		}
		
	}
	
	public void move_up()
	{
		//déplacement vers le haut	
		for(int l = 0;l < size;l++) // lignes
		{
			for(int c = 1; c < size; c++) // colonnes 
			{
				
				if(blocTab[l][c] != -1) // si la case contient un bloc
				{
					// on va lire toutes les colonnes qui précèdent ce bloc, et si on détecte du vide ou un bloc semblable, on effectue un décalage
					
					int idx = c; // on mémorise la colonne à laquelle on se trouve
					
					while(idx >= 0 &&  (blocTab[l][idx] == -1 || blocTab[l][idx] == blocTab[l][c]))
					{idx --;}
					
					if(c != idx+1)// si on a au moins bougé d'une case
					{
						if(blocTab[l][idx+1] == blocTab[l][c]) // si le dernier bloc a la même valeur que le bloc actuel
						{
							blocTab[l][idx+1] *= 2; // on double la valeur de la case du fond
							blocTab[l][c] = -1;//on vide l'ancienne case du bloc
						}
						else
						{
							blocTab[l][idx+1] = blocTab[l][c];//on déplace le bloc actuel
							blocTab[l][c] = -1;//on vide l'ancienne case du bloc
						}
					}
					
				}
				
			}
		}
	}
	
	public void move_right()
	{
		//déplacement vers la droite	
				for(int l = size-1;l >= 0;l--) // lignes
				{
					for(int c = size-1; c >= 0; c--) 
					{
						
						if(blocTab[c][l] != -1) // si la case contient un bloc
						{
							// on va lire toutes les colonnes qui précèdent ce bloc, et si on détecte du vide ou un bloc semblable, on effectue un décalage
							
							int idx = c; // on mémorise la colonne à laquelle on se trouve
							
							while(idx <= size-1 &&  (blocTab[idx][l] == -1 || blocTab[idx][l] == blocTab[c][l]))
							{idx ++;}
							
							if(c != idx-1)// si on a au moins bougé d'une case
							{
								if(blocTab[idx-1][l] == blocTab[c][l]) // si le dernier bloc a la même valeur que le bloc actuel
								{
									blocTab[idx-1][l] *= 2; // on double la valeur de la case du fond
									blocTab[c][l] = -1;//on vide l'ancienne case du bloc
								}
								else
								{
									blocTab[idx-1][l] = blocTab[c][l];//on déplace le bloc actuel
									blocTab[c][l] = -1;//on vide l'ancienne case du bloc
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
					// on va lire toutes les colonnes qui précèdent ce bloc, et si on détecte du vide ou un bloc semblable, on effectue un décalage
					
					int idx = c; // on mémorise la colonne à laquelle on se trouve
					
					while(idx <= size-1 &&  (blocTab[l][idx] == -1 || blocTab[l][idx] == blocTab[l][c]))
					{idx ++;}
										
					if(c != idx-1)// si on a au moins bougé d'une case
					{
						if(blocTab[l][idx-1] == blocTab[l][c]) // si le dernier bloc a la même valeur que le bloc actuel
						{
							blocTab[l][idx-1] *= 2; // on double la valeur de la case du fond
							blocTab[l][c] = -1;//on vide l'ancienne case du bloc
						}
						else
						{
							blocTab[l][idx-1] = blocTab[l][c];//on déplace le bloc actuel
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
		return false;
	}
	
	public boolean replay()
	{
		return false;
	}
	
}
