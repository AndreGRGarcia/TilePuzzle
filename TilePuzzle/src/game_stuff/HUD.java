package game_stuff;
import java.awt.Graphics;
import java.awt.Rectangle;

import game_objects.FPScounter;
import game_objects.GameObject;

public class HUD extends GameObject{
	
	private Game game;
	private Handler handler;
	
	private FPScounter fps;
	private boolean showFps;
	
	public HUD(Game game, Handler handler) {
		super(0, 0, 0, 0, ID.HUD);
		this.game = game;
		this.handler = handler;
		
		fps = new FPScounter(0, 0, 20, 20);
		showFps = true;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(showFps) {
			fps.render(g);
		}
	}
	
	public FPScounter getFps() {
		return fps;
	}
	
	public void setFps(FPScounter fps) {
		this.fps = fps;
	}
	
	public int getFpsValue() {
		return fps.getFpsValue();
	}
	
	public void setFpsValue(int fps) {
		this.fps.setFpsValue(fps);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}