package ChessClasses;

public class Pawn extends Piece{
	private int direction = 0;
	
	public Pawn(Point p, Colour c, Board b) {
		super(p, c, b);
		if (c == Colour.WHITE) {
			direction = -1;
		}
		else {
			direction = 1;
		}
	}
	
	protected MoveType CanMove(Point p) {
		if (super.CanMove(p) == MoveType.ILLEGAL) {
			return MoveType.ILLEGAL;
		}
		int dy = p.GetY() - this.GetLocation().GetY();
		int dx = p.GetX() - this.GetLocation().GetX();
		if (super.CanMove(p) == MoveType.ENPASSANT) {
			if (dy == direction && Math.abs(dx) == 1) {
				return MoveType.ENPASSANT;
			}
		}
		if (dy == direction) {
			if (dx == 0 && super.board.PieceAt(p) == null) {
				return MoveType.NORMAL;
			}
			else if (Math.abs(dx) == 1 && super.board.PieceAt(p).GetColour() != super.GetColour()) {
				return MoveType.NORMAL;
			}
		}
		if (dy == 2*direction &&
				dx == 0 &&
				this.board.PieceAt(p) == null &&
				this.board.PieceAt(new Point(super.GetLocation().GetX(), super.GetLocation().GetY() + direction)) == null) {
			return MoveType.DOUBLE;
		}
		return MoveType.ILLEGAL;
	}
	
	public void TryToMove(Point p) {
		MoveType mt = CanMove(p);
		if (mt != MoveType.ILLEGAL) {
			if (mt == MoveType.DOUBLE) {
				super.board.Move(super.GetLocation(), p, new Point(p.GetX(), p.GetY() - direction));
			}
			else {
				if (mt == MoveType.ENPASSANT) {
					super.board.PlacePieceAt(super.board.PieceAt(new Point(p.GetX(), p.GetY()-direction)), p);
				}
				super.board.Move(super.GetLocation(), p, null);
			}
		}
	}
}
