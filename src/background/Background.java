package background;

import character.Images;

public class Background {
	
	private static Images images = new Images(); // Images for backgrounds
	

	public static Images images() {
		return images;
	}

	public static void setImages(Images images) {
		Background.images = images;
	}

	

}
