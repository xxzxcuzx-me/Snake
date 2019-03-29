import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawingPanel extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private static int width = 800;
	private static int height = 600;
	private Snake snake;
	private Timer timer;
	private Rectangle2D outerBorder;
	private Rectangle2D innerBorder;
	private Rectangle2D border;
	private final int DELAY = 300;
	private Point currentPoint;
	private static int BORDERWIDTH = 15;
	private boolean isOver;
	public DrawingPanel() {
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.BLACK);
		
		outerBorder = new Rectangle2D.Double(0, 0, width, height);
		innerBorder = new Rectangle2D.Double(BORDERWIDTH, BORDERWIDTH, width-BORDERWIDTH*2, height-BORDERWIDTH*2);
		border = innerBorder.createIntersection(outerBorder);
		
		start();
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2D = (Graphics2D)graphics;

		graphics2D.setColor(Color.GRAY);
		graphics2D.fill(border);
		graphics2D.draw(border);
		
		if(!isOver) {
			graphics2D.setColor(Color.RED);
			Rectangle2D point = new Rectangle2D.Double(currentPoint.x, currentPoint.y, 6, 6);
			graphics2D.fill(point);
			graphics2D.draw(point);
			
			graphics2D.setColor(Color.CYAN);
			LinkedList<Pair<Integer,Integer>> positions = snake.getSegmentsPositions();
			for(int i=0; i<snake.getNumberOfSegments(); i++) {
				Rectangle2D segment = new Rectangle2D.Double(positions.get(i).getX(), positions.get(i).getY(), 8, 8);
				graphics2D.fill(segment);
				graphics2D.draw(segment);
			}
		} else {
			graphics2D.setColor(Color.RED);
			Rectangle2D point = new Rectangle2D.Double(400, 300, 100, 100);
			graphics2D.fill(point);
			graphics2D.draw(point);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		LinkedList<Pair<Integer,Integer>> positions = snake.getSegmentsPositions();
		int xPositionOfHead = positions.getFirst().getX(); 
		int yPositionOfHead = positions.getFirst().getY(); 
		if(xPositionOfHead < BORDERWIDTH || xPositionOfHead > width-BORDERWIDTH ||
				yPositionOfHead < BORDERWIDTH || yPositionOfHead > height-BORDERWIDTH) {
			gameOver();
		}
		for(int i=1; i<snake.getNumberOfSegments(); i++) {
			if(positions.get(i).getX() == xPositionOfHead && positions.get(i).getY() == yPositionOfHead) {
				gameOver();
				break;
			}
		}
		if(currentPoint.x == xPositionOfHead && currentPoint.y == yPositionOfHead) {
			currentPoint = new Point(width, height, BORDERWIDTH);
			snake.addNewSegment();
		}
		snake.move();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(!isOver) {
			if(event.getKeyCode() == KeyEvent.VK_LEFT) {
				snake.turnWest();
			}
			
			if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
				snake.turnEast();
			}
			
			if(event.getKeyCode() == KeyEvent.VK_UP) {
				snake.turnNorth();
			}
			
			if(event.getKeyCode() == KeyEvent.VK_DOWN) {
				snake.turnSouth();
			}
		} else {
			start();
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	private void start() {
		isOver = false;
		currentPoint = new Point(width, height, BORDERWIDTH);
		snake = new Snake();
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	private void gameOver() {
		isOver = true;
		timer.stop();
	}
}
