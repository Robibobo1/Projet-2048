import hevs.graphics.FunGraphics;
import java.awt.Color;

public class Windows {
	Grid gameGrid;
	FunGraphics funGraphics;
	int blocSize = 50;
	
	Windows(int width,int height)
	{
		funGraphics = new FunGraphics(width, height);
	}
	
	void insertGameGrid(Grid gameGrid)
	{
		this.gameGrid = gameGrid;
	}
	
	private Color caseColor(int x, int y)
	{
		if(gameGrid.blocTab[x][y] == -1)
		{
			return Color.WHITE;
		}
		else
		{
			int varColor = (int)(Math.log(gameGrid.blocTab[x][y]) / Math.log(2) * 25.5) ;
			Color colorOut = new Color(255,255-varColor,255-varColor);
			System.out.println(colorOut);
			return colorOut;
		}
		
	}
	
	void drawBloc(int x, int y)
	{
		int lineWidth = 5;
		funGraphics.setColor(Color.BLACK);
		funGraphics.drawFillRect(10 + x * blocSize + lineWidth,10 + y * blocSize + lineWidth, blocSize, blocSize);
		funGraphics.setColor(caseColor(x,y));
		funGraphics.drawFillRect(10 + x * blocSize + lineWidth,11 + y * blocSize + lineWidth, blocSize - lineWidth, blocSize - lineWidth);
	}
	
	void drawGrid()
	{
		for (int i = 0; i < gameGrid.size; i++) {
			for (int j = 0; j < gameGrid.size; j++) {
				drawBloc(j,i);
			}
		}
	}
	
	int start()
	{
		return Integer.parseInt(Dialogs.getString("Largeur de la grille: "));
	}
	
	char win()
	{
		return Dialogs.getChar("Bravo ! Tu as gagné ! Veux tu rejouer ? ");
	}
	
	char lose()
	{
		return Dialogs.getChar("Bravo ! Tu as gagné ! Veux tu rejouer ? ");
	}
}
