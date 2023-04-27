package player;

import general.Piece;

/* ---------------------------------------------------------------------------------
* 	 Program Name: Game.java 
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
* 2023-04-20	SA 		1.01	Add comments to methods
* 2023-04-20	SA 		1.01	Deleted unnecessary or not understood methods 
* 2023-04-22	SA 		1.01	Deleted AI vs AI, AI vs human methods and classes 
* ----------------------------------------------------------------------------------
* Future Enhancements: 
* -------------------- 	
* 1. to be though about
* 								
*/ 
public class Player {

	//global variables
	private int side; //white 0, black 1

	/**
	 * Default constructor.
	 */
	public Player() {
		
	}
	
	/**
	 * Constructor which allows to set side.
	 * @param side
	 */
	public Player(int side) {
		setSide(side);
	}
	
	/**
	 * Get side. Black or white.
	 * @return side
	 */
	public int getSide() {
		return side;
	}

	/**
	 * Set side. Black or white.
	 * @param side
	 */
	public void setSide(int side) {
		this.side = side;
	}
	
	/**
	 * Get enemy side.
	 * @return int
	 */
	public int getEnemySide() {
		if (getSide()==0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Find out if piece is owned by player.
	 * @param piece
	 * @return boolean
	 */
	public boolean isOwnPiece(Piece piece) {
		if (piece.getColor() == getSide()) {
			return true;
		} else {
			return false;
		}
	}
	
}
