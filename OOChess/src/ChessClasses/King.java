package ChessClasses;

public class King extends Piece{
	private int castle;
	
	public King(Point p, Colour c, Board b) {
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
		if (Math.abs(dy) <= 1 && Math.abs(dx) <= 1) {
			return mt;
		}
		else if (castle == 0 && Math.abs(dy) == 0 && Math.abs(dx) == 2) {
			if (dx < 0 &&
					super.board.PieceAt(new Point (0, p.GetY())) instanceof Rook) {
				if (((Rook)super.board.PieceAt(new Point(0, p.GetY()))).GetCastle() == 0 &&
						super.board.PieceAt(new Point(1, p.GetY())) == null &&
						super.board.PieceAt(new Point(2, p.GetY())) == null &&
						super.board.PieceAt(new Point(3, p.GetY())) == null) {
					super.board.PlacePieceAt(super.board.PieceAt(new Point(0, p.GetY())), new Point(3, p.GetY()));
					return MoveType.CASTLE;
				}
			}
			else if (super.board.PieceAt(new Point(7, p.GetY())) instanceof Rook) {
				if (((Rook)super.board.PieceAt(new Point(7, p.GetY()))).GetCastle() == 0 &&
						super.board.PieceAt(new Point(6, p.GetY())) == null &&
						super.board.PieceAt(new Point(5, p.GetY())) == null) {
					super.board.PlacePieceAt(super.board.PieceAt(new Point(7, p.GetY())), new Point(5, p.GetY()));
					return MoveType.CASTLE;
				}
			}
		}
		return MoveType.ILLEGAL;
	}
	
	public void SetCastle(int castle) {
		this.castle = castle;
	}
}
