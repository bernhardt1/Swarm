package Maps;

import java.util.ArrayList;
import java.util.List;

import MapBlocks.MapPiece;
import MapBlocks.RoomTrigger;
import MapBlocks.Wall;

public abstract class Map {

		protected List<List<String>> stringMap;
		protected List<MapPiece> map;
		
		String w = "W";
		String o = "O";
		String t = "T";

		public Map() {
			map = new ArrayList<MapPiece>();
			stringMap = new ArrayList<List<String>>();
		}
		
		public List<MapPiece> getMap() {
			return map;
		}
		
		public void drawingMap(){
			map = new ArrayList<MapPiece>();
			int currX = 0;
			int currY = 0;
			for (List<String> row : stringMap) {
				for (String block : row) {
					if (block.equals("W")) {
						MapPiece piece = new Wall(currX, currY);
						map.add(piece);
					} else if (block.equals("O")) {

					} else if (block.equals("T")) {
						MapPiece piece = new RoomTrigger(currX, currY);
						map.add(piece);
					}
					
					currX += MapPiece.SIZE_X;
				}
				currY += MapPiece.SIZE_Y;
				currX = 0;
			}
		}
}
