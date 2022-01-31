package game_stuff;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game_objects.Board;
import game_objects.DrawnButton;
import game_objects.GameObject;
import listeners.KeyInput;

//TODO the sizes of the images automated (should be done but img is bigger than should be)

public class Game extends JPanel implements Runnable {
	
	private static final long serialVersionUID = -6112428091888191314L;

	public static final int SCREENWIDTH = 1200, SCREENHEIGHT = SCREENWIDTH / 16 * 11;
	private Thread thread;
	private boolean running = false;
	
	private JFrame frame;
	public Handler handler;
	public HUD hud;
	public Menu menu;
	public Board board;
	public DrawnButton button;
	public Window win;
	
	public static final int XPOS = 300;
	public static final int YPOS = 10;
	
	public boolean showFps;
	
	public enum STATE {
		Menu,
		Loading,
		Game;
	}
	
	public STATE gameState = STATE.Menu;
	
	public Game(String path, String difficulty) {
		handler = new Handler(this);
		
		hud = new HUD(this, handler);
		menu = new Menu();
		button = new DrawnButton(1030, 370, "Restart", this);
		frame = new JFrame("Pervy Shit");
		
		win = new Window(SCREENWIDTH, SCREENHEIGHT, frame, this, board);
		board = new Board(XPOS, YPOS, ID.Board, path, difficulty);
		handler.addObject(board);
		handler.addObject(button);
		board.shuffle(150*(Integer.parseInt(difficulty.trim())));
		showFps = true;
		frame.addKeyListener(new KeyInput(board));
		
//		int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
//		
//	    InputMap inputMap2 = contentPane2.getInputMap(condition);
//	    ActionMap actionMap2 = contentPane2.getActionMap();
//    	
//    	String enter1 = "enter";
//	    inputMap2.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter1);
//	    actionMap2.put(enter1, new UpPressed(board));
		
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				for(GameObject g: handler.getList()) {
					if(g.getId() == ID.Button && g.getBounds().contains(p)) {
						DrawnButton db = (DrawnButton)g;
						if(db.getText().equals("Restart")) {
							stop();
							win.close();
							initApp();
						}
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
	}
	
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try{
			running = false;
			thread.join();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
//		double amountOfFrames = 144.0;
		double ns = 1000000000 / amountOfTicks;
//		double ns2 = 1000000000 / amountOfFrames;
		double delta = 0;
//		double delta2 = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
//			delta2 += (now - lastTime) / ns2;
			lastTime = now;
			while(delta >= 1) {
				tick();
				// move this block under the while to get uncapped fps or inside for capped
				if(running) {
					repaint();
					frames++;
				}
				// end
				delta--;
			}
//			while(delta2 >= 1) {
//				// move this block under the while to get uncapped fps 
//				if(running)
//					render();
//				frames++;
//				// end
//				delta2--;
//			}
			
			
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				hud.setFpsValue(frames);
				frames = 0;
			}
		}
	}
	
	private void tick() {
		handler.tick();
		
		if(gameState == STATE.Menu) {
			hud.tick();
			
		}
		
	}
	
	private void render(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, 2000, 2000);
//		g.setColor(Color.BLACK);
//		g.fillRect(XPOS-10, YPOS-10, 920, 697);
		
		if(!board.isLoading()) {
			handler.render(g);
			hud.render(g);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(500, 500, 50, 50);
		}
		
		g.dispose();
	}
	
	@Override
	public void paint(Graphics g) {
		render(g);
	}
	
	public static void printError(Exception e, String s) {
		PrintWriter pw;
		File debug = new File("Debug.txt");
		try {
			pw = new PrintWriter(debug, "UTF-8");
			s = s + " " + e.getMessage();
			pw.write(s);
			pw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void printError(String s) {
		PrintWriter pw;
		File debug = new File("Debug.txt");
		try {
			pw = new PrintWriter(debug, "UTF-8");
			pw.write(s);
			pw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public static void initApp() {
		JFrame frame = new JFrame("Pervy Project");
		frame.getContentPane().setBackground(Color.PINK);
		frame.setPreferredSize(new Dimension(500, 130));
		frame.setMaximumSize(new Dimension(500, 130));
		frame.setMinimumSize(new Dimension(500, 130));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JLabel infoLabel = new JLabel("Browse what picture to use");
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new BorderLayout());
		JTextField imagePathTF = new JTextField();
		JButton browse = new JButton("Browse");
		browse.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				String fileName = "";
				
				if(chooser.showSaveDialog(chooser) == JFileChooser.APPROVE_OPTION) {
				    fileName = chooser.getSelectedFile().toString();
				} else {
					return;
				}
				imagePathTF.setText(fileName);
    		}
		});
		
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new BorderLayout());
		JLabel difficultyLabel = new JLabel("Choose the difficulty:");
		String[] options = {"    3", "    4", "    5", "    6", "    7", "    8", "    9", "    10"};
		JComboBox difficultyCB = new JComboBox(options);
		
		upperPanel.add(imagePathTF, BorderLayout.CENTER);
		upperPanel.add(browse, BorderLayout.EAST);
		
		lowerPanel.add(difficultyLabel, BorderLayout.CENTER);
		lowerPanel.add(difficultyCB, BorderLayout.EAST);
		
		centerPanel.add(upperPanel, BorderLayout.CENTER);
		centerPanel.add(lowerPanel, BorderLayout.SOUTH);
		
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!imagePathTF.getText().isEmpty()) {
					frame.dispose();
					new Game(imagePathTF.getText(), (String)difficultyCB.getSelectedItem());
				}
			}
		});
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(infoLabel, BorderLayout.NORTH);
		mainPanel.add(confirm, BorderLayout.SOUTH);
		
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}