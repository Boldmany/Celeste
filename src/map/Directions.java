package map;

public enum Directions {
	UP(0x1),
	DOWN(0x2),
	LEFT(0x4),
	RIGHT(0x8);
	
	private final int n;
	
	private Directions(int n) {
		this.n = n;
	}
	
	public int getValue() {
		return n;
	}
	
	public static boolean has(int directions, int has) {
		return (directions & has) == has;
	}
}
