package ChessClasses;

public class Queen extends Piece{
	public Queen(Point p, Colour c, Board b) {
		super(p, c, b);
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
				if (super.board.PieceAt(new Point(p.GetX()+dx/Math.abs(dx)*i,
						p.GetY())) != null) {
					return MoveType.ILLEGAL;
				}
			}
			return mt;
		}
		else if (dy != 0 && dx == 0) {
			for (int i=1; i<Math.abs(dy); i++) {
				if (super.board.PieceAt(new Point(p.GetX(),
						p.GetY()+dx/Math.abs(dx)*i)) != null) {
					return MoveType.ILLEGAL;
				}
			}
			return mt;
		}
		else if (Math.abs(dy) == Math.abs(dx)) {
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
