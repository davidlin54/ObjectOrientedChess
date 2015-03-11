package ChessClasses;

public class Point {
	private int x;
	private int y;
	
	public Point(int i, int j) {
		this.x = i;
		this.y = j;
	}
	
	public int GetX() {
		return this.x;
	}
	
	public int GetY() {
		return this.y;
	}
	
	public void SetX(int i) {
		this.x = i;
	}
	
	public void SetY(int j) {
		this.y = j;
	}
	
	public void SetPoint(int i, int j) {
		SetX(i);
		SetY(j);
	}
}
