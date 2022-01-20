import hevs.graphics.FunGraphics;
import java.awt.Color;

public class Windows {
	Grid gameGrid;
	//Creation d'un objet fungraphics
	FunGraphics funGraphics;
	// Taille du bloc
	int blocSize = 50;
	// Epaisseur des bords des blocs
	int lineWidth = 4;
	// Offset de la grille 
	int gridOffsetX, gridOffsetY, gridSize;
	// Taille de la fenetre
	int winWidth, winHeight;

	// width -> Largeur de la fenetre
	// height -> Hauteur de la fenetre
	Windows(int width, int height) {
		// Nomme la fenetre
		funGraphics = new FunGraphics(width, height, "2048 HES Style");
		// Remplis les attributs
		winWidth = width;
		winHeight = height;
	}

	// Insertion de l'objet gameGrid
	void insertGameGrid(Grid gameGrid) {
		this.gameGrid = gameGrid;
		// Calcule l'offset de la grille pour etre toujours au milieu de la fenetre
		gridSize = lineWidth + (blocSize - lineWidth) * gameGrid.size;
		gridOffsetX = (winWidth - gridSize) / 2;
		gridOffsetY = (winHeight - gridSize) / 2;
	}

	// Retourne une variable de type color selon la valeur du bloc choisis
	// La couleur choisis ici est le rouge
	private Color blocColor(int x, int y) {
		// Si la case est vide, colorier en blanc
		if (gameGrid.blocTab[x][y] == -1) {
			return Color.WHITE;
		} else {
			// Pour avoir une couleur qui change lineairement, il faut faire une fonction log2
			// La valeur change de 0-12, puis de 0-255 en faisant * 21
			int varColor = (int) (Math.log(gameGrid.blocTab[x][y]) / Math.log(2) * 21);
			Color colorOut = new Color(255, 255 - varColor, 255 - varColor);
			return colorOut;
		}
	}

	// Dessine un bloc sur la fenetre funGraphics
	void drawBloc(int x, int y) {
		// Calcule la position du bloc
		int blocXPosition = gridOffsetX + x * (blocSize - lineWidth);
		int blocYPosition = gridOffsetY + y * (blocSize - lineWidth);

		// Dessine un carre noir
		funGraphics.setColor(Color.BLACK);
		funGraphics.drawFillRect(blocXPosition, blocYPosition, blocSize, blocSize);

		// Dessine un carre d'une nuance de rouge lineWidth plus petit que blocsize
		funGraphics.setColor(blocColor(x, y));
		funGraphics.drawFillRect(blocXPosition + lineWidth, blocYPosition + lineWidth, blocSize - (2 * lineWidth),
				blocSize - (2 * lineWidth));

		// Ecrit la valeur de la case et la decale selon le nombre de dizaines
		String blocValue = "" + gameGrid.blocTab[x][y];
		if (gameGrid.blocTab[x][y] != -1) {
			funGraphics.drawString(blocXPosition + 24 - 4 * blocValue.length(), blocYPosition + 30, blocValue,
					Color.BLACK, 15);
		}
	}

	// Dessine le tableau a l'aide de drawBloc
	void drawGrid() {
		for (int i = 0; i < gameGrid.size; i++) {
			for (int j = 0; j < gameGrid.size; j++) {
				drawBloc(j, i);
			}
		}
	}

	// Routine de début de jeu
	int start() {
		// Reinitialise le jeu
		funGraphics.setColor(Color.WHITE);
		funGraphics.drawFillRect(0, 0, winWidth, winHeight);

		// Demande a l'utilisateur la taille de grille
		while (true) {
			char usrChar = Dialogs.getChar("Largeur de la grille: (1 - 8)");
			if (usrChar >= '1' && usrChar <= '8')
				return usrChar - '0';
		}
	}

	// Routine de victoire
	boolean win() {
		// Demande a l'utilisateur si il veut rejouer
		while (true) {
			char usrChar = Dialogs.getChar("Bravo ! Tu as gagné ! Veux tu rejouer ? y/n");
			if (usrChar == 'y')
				return true;
			if (usrChar == 'n')
				return false;
		}
	}

	// Routine de defaite
	boolean lose() {
		// Demande a l'utilisateur si il veut rejouer
		while (true) {
			char usrChar = Dialogs.getChar("Tu as perdu ! Veux tu quand même rejouer ? y/n");
			if (usrChar == 'y')
				return true;
			if (usrChar == 'n')
				return false;
		}
	}
}
