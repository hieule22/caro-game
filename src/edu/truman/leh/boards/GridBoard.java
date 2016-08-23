package edu.truman.leh.boards;

import java.util.Arrays;

import edu.truman.leh.elements.Piece;
import edu.truman.leh.elements.Position;

/**
 * A board that stores the cells in 
 * a 2-dimensional array.
 * @author Hieu Le
 * @version November 2nd, 2015
 */
public class GridBoard implements Board
{
   private int height;
   private int width;
   private Piece[][] cells;
   
   /**
    * Constructs a new grid board
    * @param height the board's height
    * @param width the board's column
    */
   public GridBoard(int height, int width)
   {
      this.height = height;
      this.width = width;
      cells = new Piece[height][width];
      // Empty the board
      for (int r = 0; r < height; r++)
         Arrays.fill(cells[r], Piece.EMPTY);
   }

   @Override
   public int getWidth()
   {
      return width;
   }

   @Override
   public int getHeight()
   {
      return height;
   }

   @Override
   public void assignPiece(Position pos, Piece piece)
   {
      cells[pos.getRow()][pos.getColumn()] = piece;
   }

   @Override
   public Piece getPiece(Position pos)
   {
      return cells[pos.getRow()][pos.getColumn()];
   }

   @Override
   public boolean isLegalPosition(Position pos)
   {
      int row = pos.getRow();
      int column = pos.getColumn();
      if (row < 0 || row >= height)
         return false;
      if (column < 0 || column >= width)
         return false;
      return true;
   }
}
