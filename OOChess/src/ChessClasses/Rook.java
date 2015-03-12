package ChessClasses;

public class Rook extends Piece{
	private int castle;
	
	public Rook(Point p, Colour c, Board b) {
		super(p, c, b);
		castle = 0;
	}
	
	protected MoveType CanMove(Point p) {
		MoveType mt = super.CanMove(p);
		if (mt == MoveType.ILLEGAL) {
			return MoveType.ILLEGAL;
		}
		int dy = p.GetY() - super.GetLocation().GetY();
		int dx = p.GetX() - super.GetLocation().GetX();
		if (dy == 0 && dx != 0) {
			for (int i=1; i<Math.abs(dx); i++) {
				if (super.board.PieceAt(new Point(super.GetLocation().GetX()+dx/Math.abs(dx)*i,
						super.GetLocation().GetY())) != null) {
					return MoveType.ILLEGAL;
				}
			}
			return mt;
		}
		else if (dy != 0 && dx == 0) {
			for (int i=1; i<Math.abs(dy); i++) {
				if (super.board.PieceAt(new Point(super.GetLocation().GetX(),
						super.GetLocation().GetY()+dx/Math.abs(dx)*i)) != null) {
					return MoveType.ILLEGAL;
				}
			}
			return mt;
		}
		return MoveType.ILLEGAL;
	}
	
	public void SetCastle(int castle) {
		this.castle = castle;
	}
	public int GetCastle() { 
		return castle;
	}
}
