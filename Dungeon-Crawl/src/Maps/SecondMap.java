package Maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import MapBlocks.MapPiece;
import MapBlocks.Wall;

public class SecondMap extends Map{

	public SecondMap() {
		super();
		drawMap();
	}

	private void drawMap() {
		String w = "W";
		String o = "O";
		List<String> l0 = Arrays.asList(w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w);
		List<String> l1 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l2 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l3 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l4 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l5 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l6 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l7 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l8 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l9 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l10 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l11 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l12 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l13 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l14 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l15 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l16 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l17 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l18 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l19 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l20 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l21 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l22 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l23 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l24 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l25 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l26 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l27 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l28 = Arrays.asList(w, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, w);
		List<String> l29 = Arrays.asList(w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w);
		stringMap.add(l0);
		stringMap.add(l1);
		stringMap.add(l2);
		stringMap.add(l3);
		stringMap.add(l4);
		stringMap.add(l5);
		stringMap.add(l6);
		stringMap.add(l7);
		stringMap.add(l8);
		stringMap.add(l9);
		stringMap.add(l10);
		stringMap.add(l11);
		stringMap.add(l12);
		stringMap.add(l13);
		stringMap.add(l14);
		stringMap.add(l15);
		stringMap.add(l16);
		stringMap.add(l17);
		stringMap.add(l18);
		stringMap.add(l19);
		stringMap.add(l20);
		stringMap.add(l21);
		stringMap.add(l22);
		stringMap.add(l23);
		stringMap.add(l24);
		stringMap.add(l25);
		stringMap.add(l26);
		stringMap.add(l27);
		stringMap.add(l28);
		stringMap.add(l29);
		map = new ArrayList<MapPiece>();
		int currX = 0;
		int currY = 0;
		for (List<String> row : stringMap) {
			for (String block : row) {
				if (block.equals("W")) {
					MapPiece piece = new Wall(currX, currY);
					map.add(piece);
				} else if (block.equals("O")) {

				}
				currX += MapPiece.SIZE_X;
			}
			currY += MapPiece.SIZE_Y;
			currX = 0;
		}
	}

}
