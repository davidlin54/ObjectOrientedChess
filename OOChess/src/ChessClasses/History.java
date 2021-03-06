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
	
	public History GetNext() {
		return this.next;
	}
	
	public void Undo() {
		if (this.board.PieceAt(to) instanceof Rook) {
			((Rook) this.board.PieceAt(to)).PrevCastle();
		}
		if (this.board.PieceAt(to) instanceof King) {
			((King) this.board.PieceAt(to)).PrevCastle();
		}
		this.board.PlacePieceAt(board.PieceAt(to), from);
		if (killed != null) {
			this.board.PlacePieceAt(killed, to);
		}
		else if (this.board.PieceAt(from) instanceof King) {
			if (from.GetX() - to.GetX() == 2) {
				this.board.PlacePieceAt(this.board.PieceAt(new Point(3, from.GetY())), new Point(0, from.GetY()));
			}
			else if (from.GetX() - to.GetX() == -2){
				this.board.PlacePieceAt(this.board.PieceAt(new Point(5, from.GetY())), new Point(7, from.GetY()));
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
		this.board.SetTurn();
		this.board.SetLast(prev);
		this.board.GetLast().SetNext(this);
	}
	
	public void Redo() {
		if (next != null) {
			final History copy = this.next;
			this.board.TryToMove(next.from, next.to);
			this.board.GetLast().SetNext(copy.next);
		}
	}
}
