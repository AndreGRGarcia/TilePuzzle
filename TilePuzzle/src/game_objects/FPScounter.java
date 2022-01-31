package game_objects;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import game_stuff.ID;

public class FPScounter extends GameObject {
	
	public FPScounter(int x, int y, int width, int height) {
		super(x, y, width, height, ID.FPScounter);
	}

	private int fps = 0;
	private String fpsString = "0 fps";
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		fpsString = Integer.toString(fps) + " fps";
		
		g.setColor(Color.black);
		g.fillRect(x, y, 6 + (fpsString.length() * 7), 15);
		
		g.setColor(Color.green);
		g.setFont(new Font("Consolas", Font.PLAIN, 13));
		g.drawString(fpsString, x + 3, y+11);
		
		g.dispose();
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public void setFpsValue(int fps) {
		this.fps = fps;
	}
	
	public int getFpsValue() {
		return fps;
	}

}