package game_objects;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

import game_stuff.ID;

public class DrawnButton extends GameObject {
	
	private String text;
//	private Canvas canvas;
	private JPanel panel;
	private Color color;
	private Point p;
	
	public DrawnButton(int x, int y, String text, JPanel panel) {
		super(x, y, 10 + (int)(17.4f*text.length()), 50, ID.Button);
		this.text = text;
//		this.canvas = canvas;
		this.panel = panel;
		color = Color.white;
	}

	@Override
	public void tick() {
//		System.out.println(getBounds());
//		System.out.println(panel);
//		System.out.println(panel.getMousePosition());
		if(panel == null) return;
		
		p = panel.getMousePosition();
		
		if(p == null) return;
		
		if(getBounds().contains(p)) { // getBounds
			color = Color.gray;
		} else {
			color = Color.white;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Consolas", Font.PLAIN, 30));
		g.drawString(text, x+6, y+35);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public String getText() {
		return text;
	}
	
	
	
}
