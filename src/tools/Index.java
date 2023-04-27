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
package tools;

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
* 1. to be think about
* 								
*/
public class Index {

	//global variables
	private int x, y;
	
	/**
	 * Constructor.
	 * @param x
	 * @param y
	 */
	public Index(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get value of x.
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get value of y.
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Set value for x.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Set value of y.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}
