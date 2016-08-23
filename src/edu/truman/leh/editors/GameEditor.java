package edu.truman.leh.editors;

import java.util.Observable;
import java.util.Stack;

import edu.truman.leh.boards.Board;
import edu.truman.leh.boards.GridBoard;
import edu.truman.leh.checkers.Checker;
import edu.truman.leh.checkers.VectorChecker;
import edu.truman.leh.elements.Piece;
import edu.truman.leh.elements.Position;
import edu.truman.leh.exceptions.GameStateException;

/**
 * An object that manages the state of a game and 
 * allows queries or modifications to the game state.
 * @author Hieu Le
 * @version November 2nd, 2015
 */
public class GameEditor extends Observable
{
   private Board board;
   private Checker checker;
   private Stack<Position> moves;
   private boolean gameOver;
   
   public static final int DEFAULT_BOARD_DIMENSION = 15;
   public static final int DEFAULT_NUMBER_OF_CELLS_TO_WIN = 5;
   public static final int PLAYER_1 = 1;
   public static final int PLAYER_2 = 2;
   
   /**
    * Constructs an editor with a given board dimension
    * and number of cells to win.
    * @param height the board height
    * @param width the board width
    * @param num the number of consecutive cells to win
    */
   public GameEditor(int height, int width, int num)
   {
      if (height <= 0 || width <= 0)
         throw new IllegalArgumentException("Board dimensions must be positive");
      if (num <= 0)
         throw new IllegalArgumentException("Number of cells to win must be positive");
      board = new GridBoard(height, width);
      checker = new VectorChecker(num, board);
      moves = new Stack<Position>();
      gameOver = false;
   }
   
   /**
    * Constructs an editor with the default attributes.
    */
   public GameEditor()
   {
      this(DEFAULT_BOARD_DIMENSION, DEFAULT_BOARD_DIMENSION, DEFAULT_NUMBER_OF_CELLS_TO_WIN);
   }
   
   /**
    * Returns the height of the board
    * @return the board height
    */
   public int getBoardHeight()
   {
      return board.getHeight();
   }
   
   /**
    * Returns the width of the board
    * @return the board width
    */
   public int getBoardWidth()
   {
      return board.getWidth();
   }
   
   /**
    * Returns the last player to make a move.
    * @return the integer denoting the player
    * @throws GameStateException if the game has not started
    */
   public int getLastPlayer()
   {
      if (moves.isEmpty())
         throw new GameStateException("No last player exists for unstarted game");
      else if ((moves.size() & 1) == 0) // If the number of moves made is even
         return PLAYER_2;               // then return the second player
      else 
         return PLAYER_1;
   }
   
   /**
    * Returns the next player to make a move.
    * @return the integer denoting the player
    * @throws GameStateException if the game has already finished
    */
   public int getNextPlayer()
   {
      if (gameOver)
         throw new GameStateException("No next player for finished game");
      else if ((moves.size() & 1) == 0) // If the number of moves made is even
         return PLAYER_1;               // then return the first player
      else 
         return PLAYER_2;
   }
   
   /**
    * Returns whether the game is over.
    * @return true if the game is over, false otherwise
    */
   public boolean isGameOver()
   {
      return gameOver;
   }
   
   /**
    * Returns the winning streak resulted from the last move.
    * If the game has not terminated, an empty array is returned.
    * @return the winning streak
    */
   public Position[] getStreak()
   {
      Position pos = moves.peek();
      return checker.getStreak(pos, board.getPiece(pos));
   }
   
   /**
    * Assign a piece to a particular position.
    * Invalid assignment is ignored
    * @param pos the position to be assigned to
    */
   public void assignPiece(Position pos)
   {
      // Does nothing if the game is over or the position is illegal
      // or the position is already occupied by a non-empty piece
      if (gameOver || !board.isLegalPosition(pos) || board.getPiece(pos) != Piece.EMPTY)
         return;
      // The first player is defaulted to own the black piece
      Piece piece;
      if (getNextPlayer() == PLAYER_1) 
         piece = Piece.BLACK;
      else
         piece = Piece.WHITE;
      
      // Update the game state and notify observers
      board.assignPiece(pos, piece);
      moves.push(pos);
      gameOver = checker.isGameOver(pos, piece);
      setChanged();
      notifyObservers(pos);
   }
   
   /**
    * Returns the piece stored at a given position.
    * @param pos the cell position to query
    * @return the piece type stored at that cell
    * @throws IllegalArgumentException if the caller
    * attempts to access an illegal board position
    */
   public Piece getPiece(Position pos)
   {
      if (!board.isLegalPosition(pos))
         throw new IllegalArgumentException("Access to invalid board position");
      return board.getPiece(pos);
   }
   
   /**
    * Undo the last assignment. This request is ignored 
    * if the game has not started or is already over.
    */
   public void undo()
   {
      // Does nothing if the game has not started or is already over
      if (moves.isEmpty() || isGameOver())
         return;
      undoLastMove();
   }
   
   /**
    * Clears all cells and restore to starting game condition.
    */
   public void reset()
   {
      while (!moves.isEmpty())
      {
         undoLastMove();
      }
   }
   
   /**
    * Undo the last made move.
    */
   private void undoLastMove()
   {
      Position pos = moves.pop();
      board.assignPiece(pos, Piece.EMPTY);
      gameOver = false;
      setChanged();
      notifyObservers(pos);
   }
   
   /**
    * Returns the number of moves made so far.
    * This ignores any move that is undone.
    * @return the number of moves
    */
   public int getNumberOfMoves()
   {
      return moves.size();
   }
}
