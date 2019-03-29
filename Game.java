import javax.swing.JFrame;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;

	public Game(String gameName) {
		super(gameName);
		
		setResizable(false);
		DrawingPanel panel = new DrawingPanel();
		add(panel);
		addKeyListener(panel);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
    	
	  	@SuppressWarnings("unused")
		Game game = new Game("Snake");
  	}
}
