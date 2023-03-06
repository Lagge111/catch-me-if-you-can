package map;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a map generator, which generates the game map.
 */
public class MapGenerator {

	private int brickValue;
	private int coinsValue;
	private MapImg img;
	private double brickSize = Constants.BRICK_SIZE;
	private MapData importedData;
	private int level;

	public MapGenerator(MapData importedData, int level) {
		this.importedData = importedData;
		this.level = level;
		img = new MapImg();
	}

	public void delegate(GraphicsContext gc) {
		if (gc != null) {
			if (level == 1) {
				generateCity(gc);
			} else if (level == 2) {
				generateWildWest(gc);
			}
		}
	}

	public void generateCity(GraphicsContext gc) {
		double xCord = 0;
		double yCord = 0;
		int noOfHouses = 0;
		double noOfTurns = 0;
		int bankPart = 0;

		for (int i = 0; i < MapData.NO_OF_ROW; i++) {
			for (int j = 0; j < MapData.NO_OF_COL; j++) {
				brickValue = importedData.getLevelPos(i, j, level);
				coinsValue = importedData.getItemPos(i, j, level);
				if (noOfTurns % MapData.NO_OF_COL == 0 && noOfTurns != 0) {
					xCord = 0;
					yCord = yCord + brickSize;
				}

				if (brickValue == -1 || brickValue == -2) {
					gc.drawImage(img.getBank()[bankPart], xCord, yCord, brickSize, brickSize);
					bankPart = bankPart + 1;
				} else if (brickValue == 0) {
					gc.drawImage(img.printHouses(noOfHouses), xCord, yCord, brickSize, brickSize);
					noOfHouses = noOfHouses + 1;
					if (noOfHouses == img.getHouses().size()) {
						noOfHouses = 0;
					}

				} else {
					gc.drawImage(img.printRoads(brickValue), xCord, yCord, brickSize, brickSize);
					if (coinsValue == 1) {
						gc.drawImage(img.getCoin(), xCord, yCord, brickSize, brickSize);
					} else if (coinsValue == 2) {
						gc.drawImage(img.getPowerup(), xCord, yCord, brickSize, brickSize);
					} else if (coinsValue == 3) {
						gc.drawImage(img.getPowerup(), xCord, yCord, brickSize, brickSize);
					}
				}
				xCord = xCord + brickSize;
				noOfTurns = noOfTurns + 1;
			}
		}
	}

	public void generateWildWest(GraphicsContext gc) {
		double xCord = 0;
		double yCord = 0;
		int noOfHouses = 0;
		double noOfTurns = 0;

		for (int i = 0; i < MapData.NO_OF_ROW; i++) {
			for (int j = 0; j < MapData.NO_OF_COL; j++) {
				brickValue = importedData.getLevelPos(i, j, level);
				coinsValue = importedData.getItemPos(i, j, level);
				if (noOfTurns % MapData.NO_OF_COL == 0 && noOfTurns != 0) {
					xCord = 0;
					yCord = yCord + brickSize;
				}

				if (brickValue == 0) {
					gc.drawImage(img.printWildWest(noOfHouses), xCord, yCord, brickSize, brickSize);
					noOfHouses = noOfHouses + 1;
					if (noOfHouses == img.getWildWest().size()) {
						noOfHouses = 0;
					}
				} else if (brickValue == 1 || brickValue == 2) {
					gc.drawImage(img.printSand(), xCord, yCord, brickSize, brickSize);
					if (coinsValue == 1) {
						gc.drawImage(img.getCoin(), xCord, yCord, brickSize, brickSize);
					} else if (coinsValue == 2) {
						gc.drawImage(img.getPowerup(), xCord, yCord, brickSize, brickSize);
					} else if (coinsValue == 3) {
						gc.drawImage(img.getPowerup(), xCord, yCord, brickSize, brickSize);
					}
				}
				xCord = xCord + brickSize;
				noOfTurns = noOfTurns + 1;
			}
		}
	}

}
