package listeners;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import game_objects.Board;

public class LeftPressed extends AbstractAction {
	
	private static final long serialVersionUID = -1093923631998023378L;
	
	private Board board;
	
	public LeftPressed(Board board) {
		this.board = board;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!board.isMoving()) {
			board.setMoving(true);
			board.moveLeft(true);
		}
	}	
	
}
