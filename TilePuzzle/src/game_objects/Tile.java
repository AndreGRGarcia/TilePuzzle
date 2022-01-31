package game_objects;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game_stuff.ID;

public class Tile extends GameObject {
	
	private Board board;
	private int boardX, boardY;
	private int xPos, yPos;
	private int oldXPos, oldYPos;
	public BufferedImage imgWithoutNums;
	public BufferedImage imgWithNums;
//	private boolean firstMove = true;
//	private double inicTime = 0.0;
//	private double curTime = 0.0;
	private int difference = 8;
	public int num;
	
//	private static final double ANIMATION_LENGTH = 500.0; //miliseconds
	
	public Tile(int boardX, int boardY, int xPos, int yPos, int width, int height, ID id, Board board, int num) {
		super(boardX + ((xPos-1)*width), boardY + ((yPos-1)*height), width, height, id);
		this.boardX = boardX;
		this.boardY = boardY;
		this.board = board;
		this.xPos = xPos;
		this.yPos = yPos;
		this.oldXPos = xPos;
		this.oldYPos = yPos;
		this.num = num;
	}
	
	public Tile(int boardX, int boardY, int xPos, int yPos, int width, int height, ID id, BufferedImage img, Board board, int num) {
		super(boardX + ((xPos-1)*width), boardY + ((yPos-1)*height), width, height, id, img);
		this.boardX = boardX;
		this.boardY = boardY;
		this.board = board;
		this.xPos = xPos;
		this.yPos = yPos;
		this.oldXPos = xPos;
		this.oldYPos = yPos;
		this.num = num;
	}

	@Override
	public void tick() {
		if(oldXPos != xPos) {
			if(id == ID.BlackTile) {
				oldXPos = xPos;
				return;
			}
			if(xPos < oldXPos) {
				if(board.shuffling) {
					oldXPos = xPos;
					x = boardX + ( ( xPos-1 ) * width );
					return;
				}
				if( x <= boardX + ( ( xPos-1 ) * width ) + difference) {
					x = boardX + ( ( xPos-1 ) * width );
					oldXPos = xPos;
					if(board.isSolved()) {
						board.puzzleSolved();
					} else {
						board.setMoving(false);
					}
					return;
				}
				x -= difference;
			} else {
				if(board.shuffling) {
					oldXPos = xPos;
					x = boardX + ( ( xPos-1 ) * width );
					return;
				}
				if( x >= boardX + ( ( xPos-1 ) * width ) - difference) {
					x = boardX + ( ( xPos-1 ) * width );
					oldXPos = xPos;
					if(board.isSolved()) {
						board.puzzleSolved();
					} else {
						board.setMoving(false);
					}
					return;
				}
				x += difference;
			}
		}
		
		if(oldYPos != yPos) {
			if(id == ID.BlackTile) {
				oldYPos = yPos;
				return;
			}
			if(yPos < oldYPos) {
				if(board.shuffling) {
					oldYPos = yPos;
					y = boardY + ( ( yPos-1 ) * height);
					return;
				}
				if( y <= boardY + ( ( yPos-1 ) * height ) + difference ) {
					y = boardY + ( ( yPos-1 ) * height);
					oldYPos = yPos;
					if(board.isSolved()) {
						board.puzzleSolved();
					} else {
						board.setMoving(false);
					}
					return;
				}
				y -= difference;
			} else {
				if(board.shuffling) {
					oldYPos = yPos;
					y = boardY + ( ( yPos-1 ) * height);
					return;
				}
				if( y >= boardY + ( ( yPos-1 ) * height ) - difference) {
					y = boardY + ( ( yPos-1 ) * height);
					oldYPos = yPos;
					if(board.isSolved()) {
						board.puzzleSolved();
					} else {
						board.setMoving(false);
					}
					return;
				}
				y += difference;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(id != ID.BlackTile)	{
			if(!board.isLoading())
				g.drawImage(img, x, y, null);
		}
	}
	
	public void syncPosAndOldPos() {
		oldXPos = xPos;
		oldYPos = yPos;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public int getBoardX() {
		return boardX;
	}
	
	public int getBoardY() {
		return boardY;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public int getOldXPos() {
		return oldXPos;
	}

	public int getOldYPos() {
		return oldYPos;
	}

	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	
	public Point getWindowPos() {
		return new Point(boardX + ( ( xPos-1 ) * width ), boardY + ( ( yPos-1 ) * height ));
	}
	
	public void setBlackTile() {
		id = ID.BlackTile;
		img = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
	}
	
	public void unsetBlackTile() {
		id = ID.Tile;
		img = imgWithoutNums;
	}
	
	public void setNums(boolean b) {
		if(b) {
			img = imgWithNums;
		} else {
			img = imgWithoutNums;
		}
	}
	
}