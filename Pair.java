public class Pair<X,Y> {
	private X x;
	private Y y;
	public Pair(X x, Y y) {
		this.y = y;
		this.x = x;
	}
	
	public X getX(){ return x; }
    public Y getY(){ return y; }
    public void setX(X x){ this.x = x; }
    public void setY(Y y){ this.y = y; }
}