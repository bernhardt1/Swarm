package MapBlocks;

public abstract class MapPiece {

	public static final int SIZE_X = 20;
	public static final int SIZE_Y = 20;
	private int x;
	private int y;

	public MapPiece(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
