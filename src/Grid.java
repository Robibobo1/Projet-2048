
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
		
	}
	
	public void createBloc()
	{
		
	}
	
	public String drawBloc(int posX, int posY)
	{
		return "";
	}
	
	public String drawGrid()
	{
		return "";
	}
}
