import java.util.Random;

public class Point {
	public int x;
	public int y;
	
	public Point(int width, int height, int borderWidth) {
		Random generator = new Random();
		x = (Math.abs(generator.nextInt()) % (width-borderWidth*2)) + borderWidth;
		x = x - x%10;
		y = (Math.abs(generator.nextInt()) % (height-borderWidth*2)) + borderWidth;
		y = y - y%10;
	}
}
