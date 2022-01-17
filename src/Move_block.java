
public class Move_block {
	
	int size = 5;
	int[][] blocTab = new int[size][size];
	
	
	
	public void moveBlocs(int direction)
	{				
		System.out.println("Move "+direction);

			
		//d�placement vers la gauche	
		for(int l = 0;l < size;l++) // lignes
		{
			for(int c = 1; c < size; c++) // colonnes (on commence � partir de la deuxi�me colonne car la premi�re case ne bouge pas)
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
	

	
}
