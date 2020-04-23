package tilegame;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.UIManager;

public class MenuState extends State {

	private tilegame.UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new tilegame.UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		uiManager.addObject(new UIImageButton(105, 275, 128, 64, Assets.btn_start, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
		
		handler.getMouseManager().setUIManager(uiManager);
		State.setState(handler.getGame().menuState);
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		Text.drawString(g, "Battlegrounds", 400, 50, true, Color.RED, Assets.font56);
		Text.drawString(g, "Of Legends", 400, 95, true, Color.RED, Assets.font56);
		Text.drawString(g, "_______________________________________________________", 336, 128, true, Color.BLACK, Assets.font24);
		Text.drawString(g, "Instructions", 512, 170, true, Color.BLACK, Assets.font28);
		Text.drawString(g, "--> WASD to Move", 484, 230, true, Color.BLUE, Assets.font24);
		Text.drawString(g, "--> Arrow keys to Attack", 546, 270, true, Color.BLUE, Assets.font24);
		Text.drawString(g, "--> E for inventory", 504, 310, true, Color.BLUE, Assets.font24);
		Text.drawString(g, "--> Kill both enemies", 511, 350, true, Color.BLUE, Assets.font24);
		Text.drawString(g, "--> Break trees and rocks", 553, 390, true, Color.BLUE, Assets.font24);
		Text.drawString(g, "--> Have Fun! :)", 470, 430, true, Color.BLUE, Assets.font24);	
	}

}