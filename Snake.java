import java.util.LinkedList;

public class Snake {
	private int numberOfSegments;
	private LinkedList<Pair<Integer,Integer>> segmentsPositions;
	private int velocityX;
	private int velocityY;
	private int posXOfLastSegment;
	private int posYOfLastSegment;
	private int baseVelocity = 10;
	
	public Snake() {
		numberOfSegments = 5;
		segmentsPositions = new LinkedList<Pair<Integer,Integer>>();
		for(int i=0; i<numberOfSegments; i++) {
			segmentsPositions.add(new Pair<Integer,Integer>(400-i*10, 300));
		}

		velocityX = 1*baseVelocity;
		velocityY = 0*baseVelocity;
	}

	public int getNumberOfSegments() {
		return numberOfSegments;
	}
	
	public int getPosXOfHead() {
		return segmentsPositions.getFirst().getX();
	}

	public int getPosYOfHead() {
		return segmentsPositions.getFirst().getY();
	}
	
	public LinkedList<Pair<Integer,Integer>> getSegmentsPositions(){
		return segmentsPositions;
	}
	
	public void turnNorth() {
		if(velocityY != 1*baseVelocity) {
			velocityX = 0*baseVelocity;
			velocityY = -1*baseVelocity;
		}
	}
	
	public void turnSouth() {
		if(velocityY != -1*baseVelocity) {
			velocityX = 0*baseVelocity;
			velocityY = 1*baseVelocity;
		}
	}
	
	public void turnWest() {
		if(velocityX != 1*baseVelocity) {
			velocityX = -1*baseVelocity;
			velocityY = 0*baseVelocity;
		}
	}
	
	public void turnEast() {
		if(velocityX != -1*baseVelocity) {
			velocityX = 1*baseVelocity;
			velocityY = 0*baseVelocity;
		}
	}
	
	public void move() {
		posXOfLastSegment = segmentsPositions.getLast().getX();
		posYOfLastSegment = segmentsPositions.getLast().getY();
		segmentsPositions.pollLast();
		Pair<Integer,Integer> newFirst = new Pair<Integer,Integer>(segmentsPositions.getFirst().getX() + velocityX, segmentsPositions.getFirst().getY() + velocityY);
		segmentsPositions.addFirst(newFirst);
	}
	
	public void addNewSegment() {
		segmentsPositions.addLast(new Pair<Integer,Integer>(posXOfLastSegment, posYOfLastSegment));
		numberOfSegments++;
	}
}
