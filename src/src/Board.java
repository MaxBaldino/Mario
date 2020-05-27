import java.util.ArrayList;

public class Board {

	private ArrayList<Tile> allTiles = new ArrayList<Tile>();
	
	//Add tiles here, there might be an easier way but it might be manual
	public Board() {
		allTiles.add(new Tile(0, 0));
	}
	
	public boolean isTile(int x, int y) {
		for (int i = 0; i < allTiles.size(); i++) {
			if (allTiles.get(i).getX() == x && allTiles.get(i).getY() == y) {
				return true;
			}
		}
		return false;
	}

	
}
