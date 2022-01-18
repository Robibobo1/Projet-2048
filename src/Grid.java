
//import java.lang.Math;
import java.util.Random;
import hevs.graphics.FunGraphics;

public class Grid {
	int size;
	int blocTab[][];

	Grid(int size) {
		this.size = size;
		blocTab = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				blocTab[j][i] = -1;
			}
		}

	}

	public void moveBlocs(int direction) {
		int x = 0, y = 0;// cases du tableau
		int empty = 0;// compte les cases vides
		int bloc = 0;// récupère le bloc désiré dans la grille
		int previous_bloc = -1; // bloc précédent

		// boucles permettant de scanner la grille
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// choix des valeurs de line et column selon direction
				if (direction == 2 || direction == 1) {
					x = size - 1 - i;
					y = size - 1 - j;
				}
				if (direction == 3 || direction == 0) {
					x = i;
					y = j;
				}

				if (direction == 2 || direction == 3)
					bloc = blocTab[y][x];
				if (direction == 1 || direction == 0)
					bloc = blocTab[x][y];

				if (bloc != -1) // si la case contient un bloc
				{
					int idx = 1;

					if (bloc == previous_bloc) // si le bloc précédent est le même que l'actuel
					{
						empty++;// on bouge d'une case en plus
						idx = 2;// on double la valeur du bloc
						previous_bloc = -1;
					} // réinitialisation de la variable
					else
						previous_bloc = bloc; // sauvegarde du bloc actuel

					if (empty != 0) // si on peut au moins bouger d'une case
					{
						if (direction == 3 || direction == 0) // on inverse la variable empty selon la direction
							empty *= -1;

						// déplacement des blocs selon le sens
						if (direction == 2 || direction == 3) {
							blocTab[y + empty][x] = blocTab[y][x] * idx;
							blocTab[y][x] = -1;
						}

						if (direction == 1 || direction == 0) {
							blocTab[x][y + empty] = blocTab[x][y] * idx;
							blocTab[x][y] = -1;
						}

						empty = Math.abs(empty); // redressage de la variable empty
					}
				} else
					empty++;
			}
			empty = 0; // reset de empty à chaque changement de lignes
			previous_bloc = -1; // reset de la variable previous bloc
		}
	}

	public void createBloc() {
		Random r = new Random();
		int randomX;
		int randomY;

		do {
			randomX = r.nextInt(size);
			randomY = r.nextInt(size);
		} while (blocTab[randomX][randomY] != -1);

		blocTab[randomX][randomY] = 2;
	}

	private String drawBloc(int posX, int posY) {
		String out = "";
		out += blocTab[posX][posY];
		return out;
	}

	public String drawGrid() {
		String out = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				out += (blocTab[j][i] == -1) ? " " + drawBloc(j, i) : "  " + drawBloc(j, i);
			}
			out += '\n';
		}
		return out;
	}

	public boolean is2048() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (blocTab[j][i] == 2048)
					return true;
			}
		}
		return false;
	}

}
