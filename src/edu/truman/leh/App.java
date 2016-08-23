package edu.truman.leh;

import javax.swing.JFrame;

import edu.truman.leh.editors.GameEditor;
import edu.truman.leh.ui.GameFrame;

public class App
{
   public static void main(String[] args)
   {
      GameEditor editor = new GameEditor();
      GameFrame frame = new GameFrame(editor);
      editor.addObserver(frame);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationByPlatform(true);
      frame.pack();
      frame.setMinimumSize(frame.getSize());
      frame.setVisible(true);
   }
}
