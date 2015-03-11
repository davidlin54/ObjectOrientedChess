package ChessClasses;

public class Piece {
	protected Board board;
	private Colour colour;
	private Point location;
	
	protected MoveType CanMove(Point p) {
		if (p.GetX() >= 0 && p.GetX() <= 7 && p.GetY() >= 0 && p.GetY() <= 7) {
			if (board.PieceAt(p) == null) {
				if (board.GetEnpassant() == p) {
					return MoveType.ENPASSANT;
				}
				else {
					return MoveType.NORMAL;
				}
			}
			else if (board.PieceAt(p).GetColour() != colour) {
				return MoveType.NORMAL;
			}
			return MoveType.ILLEGAL;
		}
		return MoveType.ILLEGAL;
	}
	
	public Piece(Point location, Colour colour, Board board) {
		this.board = board;
		this.colour = colour;
		this.location = location;
	}
	
	public Point GetLocation() {
		return location;
	}
	
	public Colour GetColour() {
		return colour;
	}
	
	public void SetLocation(Point p) {
		location = p;
	}
	
	public void TryToMove(Point p) {
		if (CanMove(p) != MoveType.ILLEGAL) {
			if (CanMove(p) == MoveType.ENPASSANT) {
				if (colour == Colour.WHITE) {
					board.Move(location, p, null);
				}
				else {
					board.Move(location, p, null);
				}
			}
			else {
				board.Move(location, p, null);
			}
		}
	}
}
