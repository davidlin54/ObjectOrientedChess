package ChessClasses;

public class Board {
	private Piece cboard[][] = new Piece[8][8];
	private Colour turn;
	private Point enpassant;
	
	public Board() {
		turn = Colour.WHITE;
		enpassant = null;
		Piece tempPiece = null;
		Colour pColour;
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				if (j == 0 || j == 7) {
					if (j == 0) {
						pColour = Colour.BLACK;
					}
					else {
						pColour = Colour.WHITE;
					}
					if (i == 0 || i == 7) {
						tempPiece = new Rook(new Point(i, j), pColour, this);
					}
					else if (i == 1 || i == 6) {
						tempPiece = new Knight(new Point(i, j), pColour, this);
					}
					else if (i == 2 || i == 5) {
						tempPiece = new Bishop(new Point(i, j), pColour, this);
					}
					else if (i == 3) {
						tempPiece = new Queen(new Point(i, j), pColour, this);
					}
					else {
						tempPiece = new King(new Point(i, j), pColour, this);
					}
					cboard[i][j] = tempPiece;
				}
				else if (j == 1 || j == 6) {
					if (j == 1) {
						pColour = Colour.BLACK;
					}
					else {
						pColour = Colour.WHITE;
					}
					tempPiece = new Pawn(new Point(i, j), pColour, this);
					cboard[i][j] = tempPiece;
				}
			}
		}
	}
	
	public void Move(Point p1, Point p2, Point ep) {
		if (PieceAt(p1) instanceof Rook) {
			((Rook) PieceAt(p1)).SetCastle(1);
		}
		else if (PieceAt(p1) instanceof King) {
			((King) PieceAt(p1)).SetCastle(1);
		}
		enpassant = ep;
		if (turn == Colour.WHITE) {
			turn = Colour.BLACK;
		}
		else {
			turn = Colour.WHITE;
		}
		PlacePieceAt(PieceAt(p1), p2);
		cboard[p1.GetX()][p1.GetY()] = null;
		if (PieceAt(p2) instanceof Pawn) {
			if (turn == Colour.WHITE && p2.GetY() == 4 && p1.GetY() == 6) {
				enpassant = new Point(p2.GetX(), p2.GetY()+1);
			}
			else if (turn == Colour.BLACK && p2.GetY() == 3 && p1.GetY() == 1) {
				enpassant = new Point(p2.GetX(), p2.GetY()-1);
			}
			else {
				enpassant = null;
			}
		}
		else {
			enpassant = null;
		}
	}
	
	public Piece PieceAt(Point p) {
		return cboard[p.GetX()][p.GetY()];
	}
	
	public void PlacePieceAt(Piece p, Point pt) {
		p.SetLocation(pt);
		cboard[pt.GetX()][pt.GetY()] = p;
	}
	
	public void TryToMove(Point p1, Point p2) {
		Piece p1piece = PieceAt(p1);
		if (p1piece != null) {
			p1piece.TryToMove(p2);
		}
	}
	
	public Point GetEnpassant() {
		return enpassant;
	}
}
