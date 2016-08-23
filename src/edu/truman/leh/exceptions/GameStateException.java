package edu.truman.leh.exceptions;

/**
 * This exception is thrown whenever a modification 
 * or query about the game contradicts its current state
 * @author Hieu Le
 * @version November 2nd, 2015
 */
public class GameStateException extends IllegalStateException
{
   private static final long serialVersionUID = 1L;

   /**
    * Constructs a new instance of exception.
    * @param message the exception message
    */
   public GameStateException(String message)
   {
      super(message);
   }
}
