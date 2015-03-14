package ChessClasses;

public class Board {
	private Piece cboard[][] = new Piece[8][8];
	private Colour turn;
	private History last;
	private Point enpassant;
	
	public Board() {
		turn = Colour.WHITE;
		last = null;
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
	
	public void Move(Point p1, Point p2) {
		if (last == null) {
			last = new History(p1, p2, this, null);
		}
		else {
			last.SetNext(new History(p1, p2, this, last));
			last = new History(p1, p2, this, last);
		}
		PlacePieceAt(PieceAt(p1), p2);
		if (PieceAt(p2) instanceof Rook) {
			((Rook) PieceAt(p2)).SetCastle();
		}
		else if (PieceAt(p2) instanceof King) {
			((King) PieceAt(p2)).SetCastle();
		}
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
		if (turn == Colour.WHITE) {
			turn = Colour.BLACK;
		}
		else {
			turn = Colour.WHITE;
		}
		/*
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				String hi = null;
				if (cboard[i][j] instanceof Pawn) {
					hi = "Pawn";
				}
				if (cboard[i][j] instanceof King) {
					hi = "King";
				}
				if (cboard[i][j] instanceof Knight) {
					hi = "Knight";
				}
				if (cboard[i][j] instanceof Queen) {
					hi = "Queen";
				}
				if (cboard[i][j] instanceof Bishop) {
					hi = "Bishop";
				}
				if (cboard[i][j] instanceof Rook) {
					hi = "Rook";
				}
				System.out.print(hi+" ");
			}
			System.out.println();
		}
		System.out.println();
		*/
	}
	
	public Piece PieceAt(Point p) {
		return cboard[p.GetX()][p.GetY()];
	}
	
	public void PlacePieceAt(Piece p, Point pt) {
		cboard[p.GetLocation().GetX()][p.GetLocation().GetY()] = null;
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
	
	public void SetLast(History n) {
		last = n;
	}
	
	public void SetEnpassant(Point n) {
		enpassant = n;
	}
	
	public void Undo() {
		last.Undo();
	}
	
	public void Redo() {
		last.Redo();
	}
	
	public void SetTurn() {
		if (turn == Colour.WHITE) {
			turn = Colour.BLACK;
		}
		else {
			turn = Colour.WHITE;
		}
	}
}
