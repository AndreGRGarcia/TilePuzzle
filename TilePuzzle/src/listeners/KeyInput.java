package listeners;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game_objects.Board;

public class KeyInput extends KeyAdapter {
	
	private Board board;
	
	public KeyInput(Board board) {
		this.board = board;
	}
	
//	public void keyTyped(KeyEvent e) {
//		int key = e.getKeyCode();
//		
//		System.out.println(key);
//	}
	
	public void keyPressed(KeyEvent e) {
		System.out.println("Key pressed");
		int key = e.getKeyCode();
		
		if(!board.isMoving()) {
			if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
				System.out.println("Up");
				board.setMoving(true);
				board.moveUp(true);
			}
			if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
				System.out.println("Down");
				board.setMoving(true);
				board.moveDown(true);
			}
			if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
				System.out.println("Left");
				board.setMoving(true);
				board.moveLeft(true);
			}
			if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
				System.out.println("Right");
				board.setMoving(true);
				board.moveRight(true);
			}			
		} else {
			System.out.println("It's moving");
		}
	}
	
	public void keyReleased(KeyEvent e) {
//		int key = e.getKeyCode();
//		
//		for(int i = 0; i < handler.object.size(); i++) {
//			GameObject tempObject = handler.object.get(i);
//			
//			if(tempObject.getId() == ID.Board) {
//				Board tempBoard = (Board)tempObject;
//				if(tempBoard.moving) {
//					if(key == KeyEvent.VK_UP) {
//						System.out.println("Up released");
//						tempBoard.stopMoving();
//					}
//					if(key == KeyEvent.VK_DOWN) {
//						System.out.println("Up released");
//						tempBoard.stopMoving();
//					}
//					if(key == KeyEvent.VK_LEFT) {
//						System.out.println("Up released");
//						tempBoard.stopMoving();
//					}
//					if(key == KeyEvent.VK_RIGHT) {
//						System.out.println("Up released");
//						tempBoard.stopMoving();
//					}
//				}
//			}
//			
//		}
	}
	
}