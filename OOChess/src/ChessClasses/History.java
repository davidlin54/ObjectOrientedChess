package ChessClasses;

public class History {
	private Board board;
	private Point from;
	private Point to;
	private Piece killed;
	private History next;
	private History prev;
	
	public History(Point from, Point to, Board board, History prev) {
		this.from = from;
		this.to = to;
		this.board = board;
		this.prev = prev;
		this.next = null;
		this.killed = board.PieceAt(to);
	}
	
	public void SetNext(History next) {
		this.next = next;
	}
	
	public void Undo() {
		
	}
	
	public void Redo() {
		
	}
}
