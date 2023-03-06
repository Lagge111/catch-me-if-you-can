package map;

/**
 * Represents the game maps, its items (coins/power-ups), and the position of
 * the NPCs. Contains getters to be able to access the data from other classes,
 * and setters to be able to change the map data, e.g. if a coin is picked up it
 * is removed from the map.
 * 
 */
public class MapData {

	public final static int NO_OF_COL = 20;
	public final static int NO_OF_ROW = 11;
	public final static int TOTAL_BRICKS = NO_OF_COL * NO_OF_ROW;
	private int[][] level1;
	private int[][] level2;
	private int[][] itemsLevel1;
	private int[][] itemsLevel2;
	private int[][] npcPos;
	private int noOfCoinsLevel1;
	private int noOfCoinsLevel2;

	public MapData() {
		level1 = new int[][] { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
				{ 0, 0, 3, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, },
				{ 0, 2, 1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 0, 0, 0, 3, -1, -2, 3, 0, },
				{ 0, 0, 3, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 6, 2, 1, 2, 2, 9, 0, },
				{ 0, 0, 3, 0, 3, 0, 0, 2, 2, 2, 1, 2, 2, 9, 0, 3, 0, 0, 3, 0, },
				{ 0, 0, 8, 2, 7, 2, 0, 0, 0, 0, 3, 0, 0, 3, 0, 8, 2, 2, 9, 0, },
				{ 0, 2, 9, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 3, 0, 0, 3, 0, },
				{ 0, 0, 8, 2, 2, 2, 2, 10, 2, 2, 9, 0, 0, 3, 0, 3, 0, 0, 3, 0, },
				{ 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 3, 0, 0, 0, 0, },
				{ 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 11, 2, 2, 7, 2, 7, 2, 2, 2, 0, },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
		};

		level2 = new int[][] { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
				{ 0, 0, 1, 1, 2, 1, 2, 1, 2, 2, 1, 0, 0, 2, 2, 2, 2, 2, 1, 0, },
				{ 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 1, 2, 2, 1, 0, 0, 0, 0, 2, 0, },
				{ 0, 1, 1, 0, 0, 2, 0, 1, 1, 2, 1, 0, 0, 1, 2, 2, 1, 0, 2, 0, },
				{ 0, 2, 0, 0, 0, 2, 0, 0, 1, 0, 2, 2, 2, 1, 0, 0, 2, 0, 2, 0, },
				{ 0, 2, 0, 1, 2, 1, 2, 2, 2, 0, 2, 0, 0, 0, 0, 0, 1, 2, 1, 0, },
				{ 0, 2, 0, 2, 0, 2, 0, 0, 2, 0, 1, 2, 2, 2, 1, 1, 2, 0, 2, 0, },
				{ 0, 2, 0, 2, 0, 2, 0, 0, 2, 0, 1, 0, 0, 0, 0, 1, 0, 0, 2, 0, },
				{ 0, 1, 2, 1, 0, 1, 0, 0, 2, 0, 2, 2, 2, 2, 2, 1, 0, 0, 2, 0, },
				{ 0, 0, 0, 1, 2, 1, 2, 2, 1, 2, 1, 0, 0, 0, 0, 1, 2, 2, 1, 0, },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }, 
		};

		itemsLevel1 = new int[][] { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
				{ 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, },
				{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, },
				{ 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 3, 0, },
				{ 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, },
				{ 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, },
				{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, },
				{ 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, },
				{ 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, },
				{ 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 0, },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
		};

		itemsLevel2 = new int[][] { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
				{ 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 1, 1, 1, 1, 1, 1, 0, },
				{ 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, },
				{ 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, },
				{ 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, },
				{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, },
				{ 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, },
				{ 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, },
				{ 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, },
				{ 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 1, 1, 1, 0, },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
		};

		// Creates a two-dimensional array without assigning it values, i.e. all the
		// values are 0 as default.
		npcPos = new int[NO_OF_ROW][NO_OF_COL];

		for (int row = 0; row < itemsLevel1.length; row++) {
			for (int col = 0; col < itemsLevel1[row].length; col++) {
				if (itemsLevel1[row][col] == 1) {
					noOfCoinsLevel1 += 1;
				}
			}
		}

		for (int row = 0; row < itemsLevel2.length; row++) {
			for (int col = 0; col < itemsLevel2[row].length; col++) {
				if (itemsLevel2[row][col] == 1) {
					noOfCoinsLevel2 += 1;
				}
			}
		}

	}

	public int getLevelPos(int x, int y, int level) {
		if (level == 1) {
			return level1[x][y];
		} else {
			return level2[x][y];
		}
	}

	public int getItemPos(int x, int y, int level) {
		if (level == 1) {
			return itemsLevel1[x][y];
		} else {
			return itemsLevel2[x][y];
		}
	}

	public void takeItem(int x, int y, int level) {
		if (level == 1) {
			itemsLevel1[x][y] = 0;
		} else {
			itemsLevel2[x][y] = 0;
		}
	}

	public void setNpcPos(int x, int y, int value) {
		npcPos[x][y] = value;
	}

	public int tryCollision(int x, int y) {
		return npcPos[x][y];
	}

	public int getNoOfCoinsLevel1() {
		return noOfCoinsLevel1;
	}

	public int getNoOfCoinsLevel2() {
		return noOfCoinsLevel2;
	}

}
