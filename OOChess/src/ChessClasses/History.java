package ChessClasses;

public class History {

	private Board board;
	private Point from;
	private Point to;
	private Piece killed;
	private History next;
	private History prev;
	
	public History(Point f, Point t, Board b, History p) {
		this.from = f;
		this.to = t;
		this.board = b;
		this.prev = p;
		this.next = null;
		this.killed = board.PieceAt(t);
	}
	
	public void SetNext(History n) {
		this.next = n;
	}
	
	public void Undo() {
		if (prev != null) {
			this.board.PlacePieceAt(board.PieceAt(to), from);
			if (killed != null) {
				this.board.PlacePieceAt(killed, to);
			}
			else if (this.board.PieceAt(from) instanceof King) {
				if (from.GetX() - to.GetX() == 2) {
					this.board.PlacePieceAt(this.board.PieceAt(new Point(3, from.GetY())), new Point(0, from.GetY()));
					((King) this.board.PieceAt(from)).SetCastle(0);
				}
				else if (from.GetX() - to.GetX() == -2){
					this.board.PlacePieceAt(this.board.PieceAt(new Point(5, from.GetY())), new Point(7, from.GetY()));
					((King) this.board.PieceAt(from)).SetCastle(0);
				}
			}
			else if (this.board.PieceAt(from) instanceof Pawn && Math.abs(to.GetX() - from.GetX()) == 1) {
				Colour colour = Colour.WHITE;
				if (this.board.PieceAt(from).GetColour() == Colour.WHITE) {
					colour = Colour.BLACK;
				}
				Piece pawn = new Pawn(new Point(to.GetX(), from.GetY()), colour, this.board);
				this.board.PlacePieceAt(pawn, new Point(to.GetX(), from.GetY()));
				this.board.SetEnpassant(to);
			}
			this.board.SetLast(prev);
			this.board.SetTurn();
		}
	}
	
	public void Redo() {
		if (next != null) {
			this.board.TryToMove(next.from, next.to);
		}
	}
}
