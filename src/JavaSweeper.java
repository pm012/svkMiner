import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import fields.Box;
import fields.Coord;
import fields.GameController;
import fields.Ranges;

public class JavaSweeper extends JFrame {
	private GameController game;
	
	private static final long serialVersionUID = -4325352426024932585L;
	private final int COLS = 9;
	private final int ROWS = 9;
	private final int BOMBS=10;
	private final int IMAGE_SIZE=50;
	JPanel panel;

	public static void main(String[] args) {
		new JavaSweeper();

	}
	
	private JavaSweeper() {
		game=new GameController(COLS, ROWS,BOMBS);
		game.start();
		setImages();
		initPanel();
		initFrame();
	}
	
	private void initPanel() {
		panel= new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for(Coord coord: Ranges.getAllCoords()) {
					
					g.drawImage((Image)game.getBox(coord).image, IMAGE_SIZE*coord.getX(), IMAGE_SIZE* coord.getY(),this);
					
				}
				
			}
		};
		panel.setPreferredSize(new Dimension(Ranges.getSize().getX()*IMAGE_SIZE, Ranges.getSize().getY()*IMAGE_SIZE));		
		add(panel);
		
	}
	
	private void initFrame() {
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Java Sweeper");
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setIconImage(getImage("icon"));
	}
	
	private void setImages() {
		for(Box box : Box.values()) {
			box.image=getImage(box.name().toLowerCase());
			
		}
	}
	
	private Image getImage(String name) {
		 String filename = "img/"+name+".png";		 
		 ImageIcon icon = new ImageIcon(getClass().getResource(filename));
		 
		 return icon.getImage();
	}

}
