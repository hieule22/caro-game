package edu.truman.leh.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import edu.truman.leh.editors.GameEditor;

/**
 * The main display frame
 * @author Hieu Le
 * @version November 2nd, 2015
 */
public class GameFrame extends JFrame implements Observer
{
   private GameEditor editor;
   private JPanel mainPanel;
   private BoardDisplayPanel displayPanel;
   private GameStatusLabel statusLabel;
   
   private static final long serialVersionUID = 1L;
   
   public GameFrame(GameEditor editor)
   {
      this.editor = editor;
      displayPanel = new BoardDisplayPanel(editor);
      statusLabel = new GameStatusLabel(editor);
      initializeGUI();
      add(mainPanel);
      setResizable(false);
   }
   
   private void initializeGUI()
   {
      mainPanel = new JPanel(new BorderLayout(3, 3));
      mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      JToolBar tools = new JToolBar();
      tools.setFloatable(false);
      mainPanel.add(tools, BorderLayout.PAGE_START);
      
      JButton resetButton = new JButton("New Game");
      resetButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            editor.reset();
         }
      });
      tools.add(resetButton);
      
      JButton undoButton = new JButton("Undo");
      undoButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            editor.undo();
         }
      });
      tools.add(undoButton);
      
      tools.addSeparator();
      tools.add(statusLabel);
      
      mainPanel.add(new JLabel(), BorderLayout.LINE_START);
      mainPanel.add(displayPanel);
   }
   
   @Override
   public void update(Observable o, Object arg)
   {
      displayPanel.update(o, arg);
      statusLabel.update(o, arg);
   }
}
