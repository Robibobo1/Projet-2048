
public class Move_block {
	
	int size = 5;
	int[][] blocTab = new int[size][size];
	
	
	
	public void moveBlocs(int direction)
	{				
		System.out.println("Move "+direction);

			
		//déplacement vers la gauche	
		for(int l = 0;l < size;l++) // lignes
		{
			for(int c = 1; c < size; c++) // colonnes (on commence à partir de la deuxième colonne car la première case ne bouge pas)
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
	

	
}
