package edu.truman.leh.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import edu.truman.leh.editors.GameEditor;

/**
 * A label that displays the status of the game
 * @author Hieu Le
 * @version November 2nd, 2015
 */
public class GameStatusLabel extends JLabel implements Observer
{
   private GameEditor editor;
   
   private static final String TURN_1 = "PLAYER 1 TURN";
   private static final String TURN_2 = "PLAYER 2 TURN";
   private static final String WIN_1 = "CONGRATULATIONS! PLAYER 1 WINS";
   private static final String WIN_2 = "CONGRATULATIONS! PLAYER 2 WINS";
   
   private static final long serialVersionUID = 1L;
   
   public GameStatusLabel(GameEditor editor)
   {
      this.editor = editor;
      updateGameStatus();
   }
   
   private void updateGameStatus()
   {
      if (!editor.isGameOver())
      {
         if (editor.getNextPlayer() == GameEditor.PLAYER_1)
            setText(TURN_1);
         else 
            setText(TURN_2);
      }
      else
      {
         if (editor.getLastPlayer() == GameEditor.PLAYER_1)
            setText(WIN_1);
         else
            setText(WIN_2);
      }
   }

   @Override
   public void update(Observable o, Object arg)
   {
      updateGameStatus();
   }
}
