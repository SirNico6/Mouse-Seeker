package graphics;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage seeker;
	
	public static void init()
	{
		seeker = Loader.ImageLoader("/seeker.png");
	}
	
}