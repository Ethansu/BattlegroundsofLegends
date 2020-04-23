package tilegame;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static Font font56, font28, font76, font24, font12;
	
	public static BufferedImage dirt, grass, stone, tree, rock;
	public static BufferedImage heart, wood;
	public static BufferedImage[] player_stand, player_down, player_up, player_left, player_right;
	public static BufferedImage[] enemy_down, enemy_up, enemy_left, enemy_right, zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] btn_start;
	public static BufferedImage inventoryScreen;

	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadimage("/textures/sheet.png"));
		
		inventoryScreen = ImageLoader.loadimage("/textures/inventoryScreen.png");
		
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		font12 = FontLoader.loadFont("res/fonts/slkscr.ttf", 12);
		font24 = FontLoader.loadFont("res/fonts/slkscr.ttf", 24);
		font76 = FontLoader.loadFont("res/fonts/slkscr.ttf", 76);
		font56 = FontLoader.loadFont("res/fonts/slkscr.ttf", 56);
		
		wood = sheet.crop(width, height, width, height);
		
		heart = sheet.crop(width * 3, height, width, height);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_stand = new BufferedImage[1];
		
		player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);
		player_right[0] = sheet.crop(width * 4, height, width, height);
		player_right[1] = sheet.crop(width * 5, height, width, height);
		player_left[0] = sheet.crop(width * 6, height, width, height);
		player_left[1] = sheet.crop(width * 7, height, width, height);
		player_stand[0] = sheet.crop(width * 2, height, width, height);
		
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		
		zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
		zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
		zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
		zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
		zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
		zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
		zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
		zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);
		
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		rock = sheet.crop(0, height * 2, width, height);

		enemy_down = new BufferedImage[2];
		enemy_up = new BufferedImage[2];
		enemy_left = new BufferedImage[2];
		enemy_right = new BufferedImage[2];
	
		enemy_down[0] = sheet.crop(0, height * 3, width, height);
		enemy_down[1] = sheet.crop(width, height * 3, width, height);
		enemy_up[0] = sheet.crop(width * 2, height * 3, width, height);
		enemy_up[1] = sheet.crop(width * 3, height * 3, width, height);
		enemy_right[0] = sheet.crop(0, height * 4, width, height);
		enemy_right[1] = sheet.crop(width, height * 4, width, height);
		enemy_left[0] = sheet.crop(width * 2, height * 4, width, height);
		enemy_left[1] = sheet.crop(width * 3, height * 4, width, height);
	}
	
}