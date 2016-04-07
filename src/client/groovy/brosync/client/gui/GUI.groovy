package brosync.client.gui

import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.gui2.DefaultWindowManager
import com.googlecode.lanterna.gui2.EmptySpace
import com.googlecode.lanterna.gui2.MultiWindowTextGUI
import com.googlecode.lanterna.gui2.Window
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory

class GUI {
	static final TerminalScreen screen
   static final MultiWindowTextGUI windowGUI
   
   static {
      screen = new TerminalScreen(new DefaultTerminalFactory().createTerminal())
      screen.startScreen()
      
      windowGUI = new MultiWindowTextGUI(
         GUI.screen,
         new DefaultWindowManager(),
         new EmptySpace(TextColor.ANSI.BLUE)
      )
   }
   
   def static pushWindow(Window window) {
      windowGUI.addWindow(window)
      windowGUI.updateScreen()
   }
   
   def static popWindow() {
      if (!windowGUI.windows.empty) {
         windowGUI.removeWindow(windowGUI.windows[0])
      }
      windowGUI.updateScreen()
   }
}
