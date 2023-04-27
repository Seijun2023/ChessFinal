/*
	Copyright (C) 2010 Petri Tuononen
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package general;
import java.util.*;
import player.Player;
  
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
* 2023-04-20	SA 		1.02	Add comments to methods
* 2023-04-20	SA 		1.03	Deleted unnecessary or not understood methods 
* ----------------------------------------------------------------------------------
* Future Enhancements: 
* -------------------- 	
* 1. to be though about
* 								
*/ 

public class Game {
	
	private int whosTurn;
	
	//get player color who's turn it is
	public int getWhosTurn() {
		return whosTurn;
	}

	//set player color who's turn it is
	public void setWhosTurn(int whosTurn) {
		this.whosTurn = whosTurn;
	}

	/**
	 * raffle which player gets which color/side to start with.
	 */
	public void raffleSide(Player player1, Player player2) {
		Random gen = new Random();
		int result = gen.nextInt(2); //0 or 1 (0=white or 1=black)
		player1.setSide(result);
		if (result==0) {
			player2.setSide(1);
		} else {
			player2.setSide(0);
		}
	}
}
