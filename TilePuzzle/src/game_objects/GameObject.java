package game_objects;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game_stuff.ID;

public abstract class GameObject {
	
	protected int x, y, width, height;
	protected ID id;
	protected BufferedImage img;
	protected int velX, velY;
	
	public GameObject(int x, int y, int width, int height, ID id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
	}
	
	public GameObject(int x, int y, int width, int height, ID id, BufferedImage img) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.img = img;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public int getVelX() {
		return velX;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getWidth() {
		return img.getWidth();
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return img.getHeight();
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public ID getId() {
		return id;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	
}