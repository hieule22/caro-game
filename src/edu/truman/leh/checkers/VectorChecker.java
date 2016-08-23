package edu.truman.leh.checkers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.truman.leh.boards.Board;
import edu.truman.leh.elements.Piece;
import edu.truman.leh.elements.Position;

/**
 * A checker that determines if a piece assignment causes
 * the game to be over by checking the rows, columns and
 * diagonals containing the position to be assigned to.
 * @author hieule
 *
 */
public class VectorChecker extends Checker
{

   public VectorChecker(int num, Board b)
   {
      super(num, b);
   }

   @Override
   public boolean isGameOver(Position pos, Piece piece)
   {
      // Check the column containing this position
      if (analyzeDirection(pos, piece, 1, 0))
         return true;
      // Check the row containing this position
      if (analyzeDirection(pos, piece, 0, 1))
         return true;
      // Check the diagonal running from top right
      if (analyzeDirection(pos, piece, 1, 1))
         return true;
      // Check the diagonal running from top left
      if (analyzeDirection(pos, piece, 1, -1))
         return true;
      return false;
   }

   @Override
   public Position[] getStreak(Position pos, Piece piece)
   {
      List<Position> streak = new ArrayList<Position>(); 
      // Check the column containing this position
      if (analyzeDirection(pos, piece, 1, 0))
         streak.addAll(Arrays.asList(getStreakByDirection(pos, piece, 1, 0)));
      if (analyzeDirection(pos, piece, 0, 1))
         streak.addAll(Arrays.asList(getStreakByDirection(pos, piece, 0, 1)));
      if (analyzeDirection(pos, piece, 1, 1))
         streak.addAll(Arrays.asList(getStreakByDirection(pos, piece, 1, 1)));
      if (analyzeDirection(pos, piece, 1, -1))
         streak.addAll(Arrays.asList(getStreakByDirection(pos, piece, 1, -1)));
      
      return streak.toArray(new Position[streak.size()]);
   }

   /**
    * Checks if the streak along a given vector constitutes 
    * a winning streak.
    * @param pos the position of the cell
    * @param piece the piece contained in the cell
    * @param dr the change in row
    * @param dc the change in column
    * @return true if this is a winning streak, false otherwise
    */
   private boolean analyzeDirection(Position pos, Piece piece, int dr, int dc)
   {
      int row = pos.getRow();
      int column = pos.getColumn();
      Board board = getBoard();
      // Search for the position with the lowest row and column values
      // that has a different piece type than the one being assigned
      Position p0 = new Position(row - dr, column - dc);
      while (board.isLegalPosition(p0) && board.getPiece(p0) == piece)
      {
         p0.setRow(p0.getRow() - dr);
         p0.setColumn(p0.getColumn() - dc);
      }
      // Search for the position with the highest row and column values
      // that has a different piece type than the one being assigned
      Position p1 = new Position(row + dr, column + dc);
      while (board.isLegalPosition(p1) && board.getPiece(p1) == piece)
      {
         p1.setRow(p1.getRow() + dr);
         p1.setColumn(p1.getColumn() + dc);
      }
      // Check for the length of the resulting streak
      int length;
      if (dr != 0)
      {
         if (p1.getRow() > p0.getRow())
            length = p1.getRow() - p0.getRow() - 1;
         else
            length = p0.getRow() - p1.getRow() - 1;
      } 
      else 
      {
         if (p1.getColumn() > p0.getColumn())
            length = p1.getColumn() - p0.getColumn() - 1;
         else
            length = p0.getColumn() - p1.getColumn() - 1;
      }
      // Compare the streak length to the winning length
      if (length != getNumberOfCellsToWin())
         return false;
      // Check for edges
      if (!board.isLegalPosition(p0) || !board.isLegalPosition(p1))
         return true;
      // Check for empty cells
      if (board.getPiece(p0) == Piece.EMPTY || board.getPiece(p1) == Piece.EMPTY)
         return true;
      return false;
   }
   
   /**
    * Returns the winning streak along this given vector.
    * This method must only be called when the game is already over
    * @param pos the position of the cell
    * @param piece the piece contained in this cell
    * @param dr the change in row
    * @param dc the change in column
    * @return an array of positions contained in this streak
    */
   private Position[] getStreakByDirection(Position pos, Piece piece, int dr, int dc)
   {
      Position[] streak = new Position[getNumberOfCellsToWin()];
      int row = pos.getRow();
      int column = pos.getColumn();
      Board board = getBoard();
      Position p0 = new Position(row - dr, column - dc);
      
      int index = 0;
      while (board.isLegalPosition(p0) && board.getPiece(p0) == piece)
      {
         streak[index++] = new Position(p0.getRow(), p0.getColumn());
         p0.setRow(p0.getRow() - dr);
         p0.setColumn(p0.getColumn() - dc);
      }
      
      Position p1 = new Position(row + dr, column + dc);
      while (board.isLegalPosition(p1) && board.getPiece(p1) == piece)
      {
         streak[index++] = new Position(p1.getRow(), p1.getColumn());
         p1.setRow(p1.getRow() + dr);
         p1.setColumn(p1.getColumn() + dc);
      }
      streak[index] = new Position(row, column);
      
      return streak;
   }
}
