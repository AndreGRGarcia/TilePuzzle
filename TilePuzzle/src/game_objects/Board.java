package game_objects;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import game_stuff.Game;
import game_stuff.ID;
import game_stuff.PervyUtils;

public class Board extends GameObject {
	
	public Tile[][] grid;
	private BufferedImage img;
	private BufferedImage[] stars;
	private boolean moving;
	public boolean shuffling = false;
	private boolean loading;
	private int starsState = -1;
	private int tickCount;
	private Tile blackTile;
	private int diff;
	
	public static final int MAX_IMG_WIDTH = 800;
	public static final int MAX_IMG_HEIGHT = 700;
	private static final int DEFAULT_SHUFFLE_TIMES = 500;
	
	public Board(int x, int y, ID id, String path, String difficulty) {
		super(x, y, 0, 0, id);
		this.moving = false;
		loading = true;
		diff = (int)new Integer(difficulty.trim());
		grid = new Tile[diff][diff];
		try {
			if(path.equals("")) {
//			img = ImageIO.read(new File("C:\\Users\\MrMamocas\\Desktop\\Backup of pervy project\\pervy1.jpg"));
//			img = ImageIO.read(new File("C:\\Users\\MrMamocas\\Desktop\\Backup of pervy project\\pervy2.jpg"));
//			img = ImageIO.read(new File("C:\\Users\\MrMamocas\\Desktop\\Backup of pervy project\\mike sullivan.jpg"));
//			img = ImageIO.read(new File("C:\\Users\\MrMamocas\\Desktop\\Backup of pervy project\\big img.jpg")); // 4k image
//			img = ImageIO.read(new File("C:\\Users\\MrMamocas\\Desktop\\Backup of pervy project\\portrait.jpg")); // this image is huge
			img = ImageIO.read(new File("C:\\Users\\MrMamocas\\Desktop\\Backup of pervy project\\numbers 4x4.png"));
//			img = ImageIO.read(new File("C:\\Users\\MrMamocas\\Desktop\\Backup of pervy project\\numbers 6x6.png"));
//			img = ImageIO.read(new File("C:\\Users\\MrMamocas\\Desktop\\Backup of pervy project\\iris.jpg"));
			
			} else {
				img = ImageIO.read(new File(path));
			}
			
			stars = new BufferedImage[8];
			for(int i = 0; i < 8; i++) {
				stars[i] = ImageIO.read(new File("C:\\Users\\MrMamocas\\Desktop\\Backup of pervy project\\stars\\stars" + (i+1) +".png"));
			}
			
		} catch (IOException e) {
			Game.printError(e, "Error on loading image: Board");
			e.printStackTrace();
		}
		int tempWidth, tempHeight, tempX, tempY;
		if(img.getWidth() > img.getHeight()) {
			tempWidth = Math.min(MAX_IMG_WIDTH, img.getWidth());
			double aspectRatioMult = (double)img.getHeight() / (double)img.getWidth();
			tempHeight = (int)(tempWidth * aspectRatioMult);
		} else {
			tempHeight = Math.min(MAX_IMG_HEIGHT, img.getHeight());
			double aspectRatioMult = (double)img.getWidth() / (double)img.getHeight();
			tempWidth = (int)(tempHeight * aspectRatioMult);
		}
		
		tempX = ( Game.SCREENWIDTH - (tempWidth) ) / 2;
		tempY = (( Game.SCREENHEIGHT - (tempHeight) ) / 2) - 10;
		
		this.x = tempX;
		this.y = tempY;
		this.width = tempWidth;
		this.height = tempHeight;
		
		
		if(img.getWidth() != width && img.getHeight() != height) {
			resizeImg(img, width, height);
		}
		
		createGrid();
		loading = false;
		
//		shuffle(DEFAULT_SHUFFLE_TIMES);
		
	}
	
	
	private void createGrid() {
//		for(int i = 0; i < BASE_NUM_OF_TILES + level; i++) {
//			for(int j = 0; j < BASE_NUM_OF_TILES + level; j++) {
//				grid[i][j] = new Tile(x, y, i+1, j+1, img.getWidth()/(BASE_NUM_OF_TILES+level), img.getHeight()/(BASE_NUM_OF_TILES+level),
//									  ID.Tile, new BufferedImage(img.getWidth()/(BASE_NUM_OF_TILES+level),
//											   img.getHeight()/(BASE_NUM_OF_TILES+level),
//											   BufferedImage.TYPE_4BYTE_ABGR), this, (BASE_NUM_OF_TILES+1)*i + j + 1);
//			}
//		}
//		
//		for(int i = 0; i < BASE_NUM_OF_TILES + level; i++) {
//			for(int j = 0; j < BASE_NUM_OF_TILES + level; j++) {
//				for(int k = 0; k < img.getWidth()/(BASE_NUM_OF_TILES+level); k++) {
//					for(int l = 0; l < img.getHeight()/(BASE_NUM_OF_TILES+level); l++) {
//						grid[i][j].getImg().setRGB(k, l, img.getRGB(k + i*grid[i][j].getWidth(), l + j*grid[i][j].getHeight()));
//					}
//				}
//			}
//		}
		
		Graphics2D g2d;
		
		for(int i = 0; i < diff; i++) {
			for(int j = 0; j < diff; j++) {
				grid[i][j] = new Tile(x, y, i+1, j+1, img.getWidth()/(diff), img.getHeight()/(diff),
						  ID.Tile, new BufferedImage(img.getWidth()/(diff),
								   img.getHeight()/(diff),
								   BufferedImage.TYPE_4BYTE_ABGR), this, (diff)*j + i + 1);
				grid[i][j].imgWithoutNums = grid[i][j].getImg();
				for(int k = 0; k < img.getWidth()/(diff); k++) {
					for(int l = 0; l < img.getHeight()/(diff); l++) {
						grid[i][j].getImg().setRGB(k, l, img.getRGB(k + i*grid[i][j].getWidth(), l + j*grid[i][j].getHeight()));
					}
				}
				grid[i][j].imgWithNums = PervyUtils.cloneImage(grid[i][j].getImg());
				g2d = grid[i][j].imgWithNums.createGraphics();
				g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
				g2d.setColor(Color.BLACK);
//				g2d.drawString(String.valueOf((diff)*j + i + 1), (int)(grid[i][j].imgWithNums.getWidth()),(int)(grid[i][j].imgWithNums.getHeight()*0.1));
				g2d.drawString(String.valueOf((diff)*j + i + 1), 3, 15);
				grid[i][j].img = grid[i][j].imgWithNums;
				
			}
		}
		
		grid[diff-1][diff-1].setBlackTile();
		blackTile = grid[diff-1][diff-1];
		
	}

	@Override
	public void tick() {
		if(starsState != -1) {
			tickCount = (tickCount+1)%4;
			if(tickCount == 0) {
				starsState++;
				if(starsState == 8) {
					starsState = -1;
				}
			}
		}
		for(int i = 0; i < diff; i++) {
			for(int j = 0; j < diff; j++) {
				grid[i][j].tick();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x-10, y-10, width+20, height+20);
		for(int i = 0; i < diff; i++) {
			for(int j = 0; j < diff; j++) {
				grid[i][j].render(g);
			}
		}
		if(starsState != -1) {
			g.drawImage(stars[starsState], (Game.SCREENWIDTH/2)-(stars[starsState].getWidth()/2), (Game.SCREENHEIGHT/2)-(stars[starsState].getHeight()/2), null);
		}
	}
	
	public void moveUp(boolean doAnimation) {
		if(blackTile.getYPos() != diff) {
			int oldX = blackTile.getXPos(), oldY = blackTile.getYPos();
			synchronized (grid) {
				grid[oldX-1][oldY].setYPos(oldY);
				grid[oldX-1][oldY-1].setYPos(oldY+1);
				grid[oldX-1][oldY-1] = grid[oldX-1][oldY];
				grid[oldX-1][oldY] = blackTile;
			}
			if(!doAnimation) {
				Tile tempTile = grid[oldX-1][oldY-1];
				tempTile.syncPosAndOldPos();
				tempTile.setY(y + ( ( tempTile.getYPos()-1 ) * tempTile.getHeight() ));
			}
		} else {
			moving = false;
		}
	}
	
	public void moveDown(boolean doAnimation) {
		if(blackTile.getYPos() != 1) {
			int oldX = blackTile.getXPos(), oldY = blackTile.getYPos();
			synchronized (grid) {
				grid[oldX-1][oldY-2].setYPos(oldY);
				grid[oldX-1][oldY-1].setYPos(oldY-1);
				grid[oldX-1][oldY-1] = grid[oldX-1][oldY-2];
				grid[oldX-1][oldY-2] = blackTile;
			}
			if(!doAnimation) {
				Tile tempTile = grid[oldX-1][oldY-1];
				tempTile.syncPosAndOldPos();
				tempTile.setY(y + ( ( tempTile.getYPos()-1 ) * tempTile.getHeight() ));
			}
		} else {
			moving = false;
		}
	}

	public void moveLeft(boolean doAnimation) {
		if(blackTile.getXPos() != diff) {
			int oldX = blackTile.getXPos(), oldY = blackTile.getYPos();
			synchronized (grid) {
				grid[oldX][oldY-1].setXPos(oldX);
				grid[oldX-1][oldY-1].setXPos(oldX+1);
				grid[oldX-1][oldY-1] = grid[oldX][oldY-1];
				grid[oldX][oldY-1] = blackTile;
			}
			if(!doAnimation) {
				Tile tempTile = grid[oldX-1][oldY-1];
				tempTile.syncPosAndOldPos();
				tempTile.setX(x + ( ( tempTile.getXPos()-1 ) * tempTile.getWidth() ));
			}
		} else {
			moving = false;
		}
	}

	public void moveRight(boolean doAnimation) {
		if(blackTile.getXPos() != 1) {
			int oldX = blackTile.getXPos(), oldY = blackTile.getYPos();
			synchronized (grid) {
				grid[oldX-2][oldY-1].setXPos(oldX);
				grid[oldX-1][oldY-1].setXPos(oldX-1);
				grid[oldX-1][oldY-1] = grid[oldX-2][oldY-1];
				grid[oldX-2][oldY-1] = blackTile;
			}
			if(!doAnimation) {
				Tile tempTile = grid[oldX-1][oldY-1];
				tempTile.syncPosAndOldPos();
				tempTile.setX(x + ( ( tempTile.getXPos()-1 ) * tempTile.getWidth() ));
			}
		} else {
			moving = false;
		}
	}
	
	public void renderImg(String path) {
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			Game.printError(e, "Error on rendering image");
			e.printStackTrace();
		}
	}
	
	public void resizeImg(BufferedImage img, int width, int height) {
		BufferedImage tempImg = new BufferedImage(width, height, img.getType());
		
		Graphics2D g2d = tempImg.createGraphics();
        g2d.drawImage(img, 0, 0, width, height, null);
        g2d.dispose();
        
        this.img = tempImg;
	}
	
	public void shuffle(int numOfTimes) {
		
		Random r = new Random();
		int choice = 0;
		int lastChoice = choice;
		shuffling = true;
		for(int i = 0; i < numOfTimes; i++) {
			while(choice == lastChoice || (blackTile.getBoardY()==diff-1 && choice==2) ||
										  (blackTile.getBoardX()==diff-1 && choice==3) ||
										  (blackTile.getBoardY()==1 && choice==0) ||
										  (blackTile.getBoardX()==1 && choice==1)) {
				choice = r.nextInt(4);
			}
			
			switch(choice) {
				case 0:
					moveUp(false);
					lastChoice = choice;
					break;
				case 1:
					moveLeft(false);
					lastChoice = choice;
					break;
				case 2:
					moveDown(false);
					lastChoice = choice;
					break;
				case 3:
					moveRight(false);
					lastChoice = choice;
					break;
			}
		}
		shuffling = false;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public Point getBlackTilePos() {
		return new Point(blackTile.getXPos(), blackTile.getYPos());
	}
	
	public Tile getBlackTile() {
		return blackTile;
	}
	
	public String toString() {
//		String content = "";
//		for(int i = 0; i < grid.length; i++) {
//			for(int j = 0; j < grid[i].length; j++) {
//				if(grid[j][i].getId() == ID.Tile) {
//					content += grid[j][i].getOldYPos() + "Img " + grid[j][i].getYPos();
//				} else if(grid[j][i].getId() == ID.BlackTile) {
//					content += grid[j][i].getOldYPos() + "b   " + grid[j][i].getYPos();
//				}
//			}
//			content += "\n";
//		}
//		return content;
		String content = "";
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				content += grid[i][j].num + " ";
			}
			content += "\n";
		}
		return content;
	}

	public boolean isSolved() {
		for(int i = 0; i < diff; i++) {
			for(int j = 0; j < diff; j++) {
				if(grid[i][j].num != (diff)*j + i + 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void puzzleSolved() {
		moving = true;
		starsState = 0;
		for(int i = 0; i < diff; i++) {
			for(int j = 0; j < diff; j++) {
				grid[i][j].setNums(false);
			}
		}
		blackTile.unsetBlackTile();
	}
	
	public boolean isMoving() {
		return moving;
	}


	public void setMoving(boolean b) {
		moving = b;
	}


	public boolean isLoading() {
		return loading;
	}
	
	
	
	
	
}