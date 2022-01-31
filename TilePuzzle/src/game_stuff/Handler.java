package game_stuff;
import java.awt.Graphics;
import java.util.LinkedList;

import game_objects.GameObject;

public class Handler {
	
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	private Game game;
	
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public void tick() {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			if(game.board.shuffling) {
				if(tempObject.getId() == ID.Board) {
					continue;
				}
			}
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.objects.remove(object);
	}
	
	public LinkedList<GameObject> getList() {
		return objects;
	}
}