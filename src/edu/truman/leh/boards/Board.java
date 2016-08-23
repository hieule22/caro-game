package edu.truman.leh.boards;

import edu.truman.leh.elements.Piece;
import edu.truman.leh.elements.Position;

/**
 * A board that stores the cells and permits queries and 
 * modifications to the board content.
 * @author Hieu Le
 * @version November 2nd, 2015
 */
public interface Board
{
   /**
    * Returns the width of this board.
    * @return the board's width
    */
   int getWidth();
   
   /**
    * Returns the height of this board.
    * @return the board's height
    */
   int getHeight();
   
   /**
    * Adds a new piece to a specified position.
    * @param pos the position to be modified
    * @param piece the piece to be added
    */
   void assignPiece(Position pos, Piece piece);
   
   /**
    * Returns the piece stored at a specified position
    * @param pos the board position to be queried
    * @return the type of the piece 
    */
   Piece getPiece(Position pos);
   
   /**
    * Checks if a specified position is legal.
    * @param pos the position to be enquired
    * @return true if this position is legal, false otherwise
    */
   boolean isLegalPosition(Position pos);
}
