package ChessClasses;

public class King extends Piece{
	public King(Point p, Colour c, Board b) {
		super(p, c, b);
	}
	
	protected MoveType CanMove(Point p) {
		MoveType mt = super.CanMove(p);
		if (mt == MoveType.ILLEGAL) {
			return MoveType.ILLEGAL;
		}
		int dy = p.GetY() - super.GetLocation().GetY();
		int dx = p.GetX() - super.GetLocation().GetX();
		if (Math.abs(dy) <= 1 && Math.abs(dx) <= 1) {
			return mt;
		}
		return MoveType.ILLEGAL;
	}
}
