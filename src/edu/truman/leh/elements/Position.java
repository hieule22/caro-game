package edu.truman.leh.elements;

/**
 * A position on the game board, specified
 * by the row and the column it is on
 * @author Hieu Le
 * @version November 2nd, 2015
 */
public class Position
{
   private int row;
   private int column;
   
   /**
    * Constructs a new position with the specified
    * row and column.
    * @param row the position's row
    * @param column the position's column
    */
   public Position(int row, int column)
   {
      this.row = row;
      this.column = column;
   }
   
   /**
    * Returns the row number this position belongs to
    * The row is numbered from the top, starting from 0
    * @return the row number
    */
   public int getRow()
   {
      return row;
   }
   
   /**
    * Assigns the row of this position to a new value.
    * @param row the new row value
    */
   public void setRow(int row)
   {
      this.row = row;
   }
   
   /**
    * Returns the column number this position belongs to
    * The column is numbered from the right, starting from 0
    * @return
    */
   public int getColumn()
   {
      return column;
   }
   
   /**
    * Assigns the column of this position to a new value.
    * @param column the new column value
    */
   public void setColumn(int column)
   {
      this.column = column;
   }
}
