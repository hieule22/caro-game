package edu.truman.leh.checkers;

import edu.truman.leh.boards.Board;
import edu.truman.leh.elements.Piece;
import edu.truman.leh.elements.Position;

/**
 * A checker that checks if a piece assignment results
 * in termination of a game.
 * @author Hieu Le
 * @version November 2nd, 2015
 */
public abstract class Checker
{
   private int numberOfCellsToWin;
   private Board board;
   
   /**
    * Constructs a checker with a specified number to win 
    * and a Board object to keep track of.
    * @param num the number of consecutive cells to win
    * @param b the associated Board
    */
   public Checker(int num, Board b)
   {
      numberOfCellsToWin = num;
      board = b;
   }
   
   /**
    * Returns the winning number of consecutive cells 
    * occupied by the same non-empty piece type.
    * @return the number of cells to win
    */
   int getNumberOfCellsToWin()
   {
      return numberOfCellsToWin;
   }
   
   /**
    * Sets the winning number of consecutive cells occupied
    * by the same non-empty piece type.
    * @param num the updated number of cells to win
    */
   void setNumberOfCellsToWin(int num)
   {
      numberOfCellsToWin = num;
   }
   
   /**
    * Returns the Board object associated with this checker.
    * @return board
    */
   public Board getBoard()
   {
      return board;
   }
   
   /**
    * Sets the Board object associated with this checker.
    * @param b the board to keep track of
    */
   public void setBoard(Board b)
   {
      board = b;
   }
   
   /**
    * Determines if the assignment of a particular piece
    * to a particular board position results in the game
    * being over.
    * @param pos the position to be assigned to
    * @param piece the piece type to assign
    * @return true if the game terminates due to this move
    */
   public abstract boolean isGameOver(Position pos, Piece piece);
   
   /**
    * Returns the winning streak caused by the assignment of 
    * a specified piece to a specified position. This function
    * will return an empty array if the game has not terminated.
    * @param pos the position to be assigned to
    * @param piece the piece type to assign
    * @return an array of Position objects specifying the 
    * winning streak
    */
   public abstract Position[] getStreak(Position pos, Piece piece);
}
