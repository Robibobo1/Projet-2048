import hevs.graphics.FunGraphics;
import java.awt.Color;

public class Windows {
	Grid gameGrid;
	FunGraphics funGraphics;
	int blocSize = 50;
	int lineWidth = 4;
	int gridOffsetX, gridOffsetY, gridSize;
	int winWidth, winHeight;

	Windows(int width, int height) {
		funGraphics = new FunGraphics(width, height);
		winWidth = width;
		winHeight = height;
	}

	void insertGameGrid(Grid gameGrid) {
		this.gameGrid = gameGrid;
		gridSize = lineWidth + (blocSize - lineWidth) * gameGrid.size;
		gridOffsetX = (winWidth - gridSize) / 2;
		gridOffsetY = (winHeight - gridSize) / 2;
	}

	private Color blocColor(int x, int y) {
		if (gameGrid.blocTab[x][y] == -1) {
			return Color.WHITE;
		} else {
			int varColor = (int) (Math.log(gameGrid.blocTab[x][y]) / Math.log(2) * 21);
			Color colorOut = new Color(255, 255 - varColor, 255 - varColor);
			return colorOut;
		}
	}

	void drawBloc(int x, int y) {
		int blocXPosition = gridOffsetX + x * (blocSize - lineWidth);
		int blocYPosition = gridOffsetY + y * (blocSize - lineWidth);

		funGraphics.setColor(Color.BLACK);
		funGraphics.drawFillRect(blocXPosition, blocYPosition, blocSize, blocSize);

		funGraphics.setColor(blocColor(x, y));
		funGraphics.drawFillRect(blocXPosition + lineWidth, blocYPosition + lineWidth, blocSize - (2 * lineWidth),
				blocSize - (2 * lineWidth));

		String blocValue = "" + gameGrid.blocTab[x][y];
		int stringOffset[] = { 22, 18, 14, 9 };
		if (gameGrid.blocTab[x][y] != -1) {
			funGraphics.drawString(blocXPosition + stringOffset[blocValue.length() - 1], blocYPosition + 30, blocValue,
					Color.BLACK, 15);
		}
	}

	void drawGrid() {
		for (int i = 0; i < gameGrid.size; i++) {
			for (int j = 0; j < gameGrid.size; j++) {
				drawBloc(j, i);
			}
		}
	}

	int start() {
		funGraphics.setColor(Color.WHITE);
		funGraphics.drawFillRect(0, 0, winWidth, winHeight);
		return Integer.parseInt(Dialogs.getString("Largeur de la grille: (4 = 4x4)"));
	}

	char win() {

		return Dialogs.getChar("Bravo ! Tu as gagné ! Veux tu rejouer ? y/n");
	}

	char lose() {
		return Dialogs.getChar("Tu as perdu ! Veux tu quand même rejouer ? y/n");
	}
}
