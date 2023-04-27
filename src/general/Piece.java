package general;
/* ---------------------------------------------------------------------------------
* 	 Program Name: Piece.java 
* 	 Date Written: April 19th, 2023
* 		   Author: Sofia Arturo Gomez,Zoey Zhao, Ashley Zhang
*         ----> Credit to Petri Tuononen 
*         Purpose: 1. Create a chess board
*                   
* ----------------------------------------------------------------------------------
* Modification History: 
* ----------------------------------------------------------------------------------
* Date			Person	CSR#	Description
* 2023-04-19	SA 		1.01	Initial Version 
* 2023-04-21	SA 		1.01	Add comments to methods
* 2023-04-22	SA 		1.01	Deleted unnecessary or not understood methods 
* ----------------------------------------------------------------------------------
* Future Enhancements: 
* -------------------- 	
* 1. Delete text based version
* 								
*/ 
public class Piece {

	//constant integer mappings for the pieces
	public static final int KING = 1;
	public static final int QUEEN = 2;
	public static final int ROOK = 3;
	public static final int KNIGHT = 4;
	public static final int BISHOP = 5;
	public static final int PAWN = 6;
	
	//constant values for the pieces
	public static final int KING_VALUE = 1000000;
	public static final int QUEEN_VALUE = 9;
	public static final int ROOK_VALUE = 5;
	public static final int KNIGHT_VALUE = 3;
	public static final int BISHOP_VALUE = 3;
	public static final int PAWN_VALUE = 1;

	//global variables
	private int color; //white 0, black 1
	private int type;
	private int row, col; //position on the board
	
	/**
	 * Default constructor.
	 */
	public Piece() {
	}
	
	/**
	 * Constructor. Defines piece color, type and position.
	 * @param color
	 * @param type
	 * @param row
	 * @param col
	 */
	public Piece(int color, int type, int row, int col) {
		this.color = color;
		this.type = type;
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Get piece type acronym.
	 * @return acro
	 */
	public String getAcronym() {
		String acro = "";
		if (getColor()==0 && getType()!=0) {
			acro = "W"; //meaning white
		} 
		if (getColor()==1) {
			acro = "B"; //meaning black
		}
		switch (getType()) {
		case 1:
			acro += "K";
			break;
		case 2:
			acro += "Q";
			break;
		case 3:
			acro += "R";
			break;
		case 4:
			acro += "N";
			break;
		case 5:
			acro += "B";
			break;	
		case 6:
			acro += "P";
			break;	
		default:
			break;	
		}
		return acro;
	}
	
	/**
	 * Get piece type as integer.
	 * @return type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Set piece type as integer.
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * Get value of the piece.
	 * @return int
	 */
	public int getValue() {
		int type = this.getType();
		int value = 0;
		switch(type) {
		case 1:
			value = KING_VALUE;
			break;
		case 2:
			value = QUEEN_VALUE;
			break;
		case 3:
			value = ROOK_VALUE;
			break;
		case 4:
			value = KNIGHT_VALUE;
			break;
		case 5:
			value = BISHOP_VALUE;
			break;
		case 6:
			value = PAWN_VALUE;
			break;
		default:
			break;
		}
		return value;
	}

	/**
	 * Get color.
	 * @return color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Set color.
	 * @param color
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * Get row.
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Set row.
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Get column.
	 * @return col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Set column.
	 * @param col
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
}
