package ChessClasses;

public class Bishop extends Piece{
	public Bishop(Point p, Colour c, Board b) {
		super(p, c, b);
	}
	
	protected MoveType CanMove(Point p) {
		MoveType mt = super.CanMove(p);
		if (mt == MoveType.ILLEGAL) {
			return MoveType.ILLEGAL;
		}
		int dy = p.GetY() - super.GetLocation().GetY();
		int dx = p.GetX() - super.GetLocation().GetX();
		if (Math.abs(dy) == Math.abs(dx)) {
			for (int i=1; i<Math.abs(dx); i++) {
				if (super.board.PieceAt(new Point(p.GetX()+dx/Math.abs(dx)*i,
						p.GetY()+dy/Math.abs(dy)*i)) != null) {
					return MoveType.ILLEGAL;
				}
			}
			return mt;
		}
		return MoveType.ILLEGAL;
	}
}
