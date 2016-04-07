package brosync.client.gui.components

import brosync.client.gui.GUI;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Window;

class ScreenComponent {
   BasicWindow window
   
   def public ScreenComponent() {
      this.window = new BasicWindow(
         hints: [Window.Hint.CENTERED]
      )
   }
   
   def public void draw(blockThread = true) {
      GUI.windowGUI.windows.each(GUI.windowGUI.&removeWindow)
      
      if (blockThread) {
         GUI.windowGUI.addWindowAndWait(window)
      } else {
         GUI.windowGUI.addWindow(window)
         GUI.windowGUI.updateScreen()
      }
   }
}
