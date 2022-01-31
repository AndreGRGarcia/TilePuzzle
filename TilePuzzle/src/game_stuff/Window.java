package game_stuff;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import game_objects.Board;

public class Window extends Canvas {

	private static final long serialVersionUID = -8255319694373975038L;
	
	private JFrame frame;
	
	public Window(int width, int height, JFrame frame, Game game, Board board) {
		
		this.frame= frame;
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
//		frame.getContentPane().addKeyListener(new KeyInput(board));
		
//		JPanel windowPanel = new JPanel();
//		int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
////		JPanel contentPane = (JPanel) frame.getContentPane();
//		
//	    InputMap inputMap = windowPanel.getInputMap(condition);
//	    ActionMap actionMap = windowPanel.getActionMap();
//    	
//    	String upArrow = "up_arrow";
//	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), upArrow);
//	    actionMap.put(upArrow, new UpPressed(board));
//	    String downArrow = "up_arrow";
//	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), downArrow);
//	    actionMap.put(upArrow, new DownPressed(board));
//	    String leftArrow = "up_arrow";
//	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), leftArrow);
//	    actionMap.put(upArrow, new LeftPressed(board));
//	    String rightArrow = "up_arrow";
//	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), rightArrow);
//	    actionMap.put(upArrow, new RightPressed(board));
//	    
	    
		
		frame.setVisible(true);
		game.start();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void close() {
		frame.dispose();
	}
	
}