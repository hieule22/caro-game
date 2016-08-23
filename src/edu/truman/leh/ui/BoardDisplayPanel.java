package edu.truman.leh.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import edu.truman.leh.editors.GameEditor;
import edu.truman.leh.elements.Piece;
import edu.truman.leh.elements.Position;

/**
 * A panel that displays the board grid and records
 * user input through button press.
 * @author Hieu Le
 * @version November 2nd, 2015
 */
public class BoardDisplayPanel extends JPanel implements Observer
{
   private GameEditor editor;
   private int height;
   private int width;
   private IndexButton[][] buttons;
   
   private static final ImageIcon EMPTY_ICON = new
         ImageIcon(new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB));
   private static final ImageIcon BLACK_ICON = new
         ImageIcon("src/icons/cross.png");
   private static final ImageIcon WHITE_ICON = new
         ImageIcon("src/icons/circle.png");
   private static final long serialVersionUID = 1L;
   
   public BoardDisplayPanel(GameEditor editor)
   {
      this.editor = editor;
      height = editor.getBoardHeight();
      width = editor.getBoardWidth();
      buttons = new IndexButton[height][width];
      initializeGUI();
   }
   
   private void initializeGUI()
   {
      Insets margin = new Insets(0, 0, 0, 0);
      for (int r = 0; r < height; r++)
      {
         for (int c = 0; c < width; c++) {
            IndexButton button = new IndexButton(r, c);
            button.setMargin(margin);
            button.setIcon(EMPTY_ICON);
            button.setBackground(Color.YELLOW);
            button.addActionListener(new ActionListener()
            {
               @Override
               public void actionPerformed(ActionEvent e)
               {
                  editor.assignPiece(button.getPosition());
               }
            });
            buttons[r][c] = button;
         }
      }
      
      setLayout(new GridLayout(0, width));
      setBorder(new LineBorder(Color.BLACK));
      for (int r = 0; r < height; r++) 
      {
         for (int c = 0; c < width; c++)
            add(buttons[r][c]);
      }
   }
   
   @Override
   public void update(Observable o, Object arg)
   {
      Position pos = (Position)arg;
      Piece piece = editor.getPiece(pos);
      if (piece == Piece.EMPTY)
         buttons[pos.getRow()][pos.getColumn()].setIcon(EMPTY_ICON);
      else if (piece == Piece.BLACK)
         buttons[pos.getRow()][pos.getColumn()].setIcon(BLACK_ICON);
      else
         buttons[pos.getRow()][pos.getColumn()].setIcon(WHITE_ICON);
      buttons[pos.getRow()][pos.getColumn()].setOpaque(false);
      
      // Highlight the button if the game is over
      if (editor.isGameOver()) 
      {
         Position[] streak = editor.getStreak();
         for (Position p : streak)
         {
            buttons[p.getRow()][p.getColumn()].setOpaque(true);
            buttons[p.getRow()][p.getColumn()].repaint();
         }
      }
   }
   
   private class IndexButton extends JButton 
   {
      private Position position;
      private static final long serialVersionUID = 1L;
      
      public IndexButton(int row, int column)
      {
         position = new Position(row, column);
      }
      
      public Position getPosition()
      {
         return position;
      }
   }
}
