package brosync.client.gui.components.menu

import brosync.client.gui.GUI;
import brosync.client.gui.components.ScreenComponent;

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.gui2.ActionListBox
import com.googlecode.lanterna.gui2.Window
import com.googlecode.lanterna.gui2.BasicWindow
import com.googlecode.lanterna.gui2.DefaultWindowManager
import com.googlecode.lanterna.gui2.EmptySpace
import com.googlecode.lanterna.gui2.GridLayout
import com.googlecode.lanterna.gui2.MultiWindowTextGUI
import com.googlecode.lanterna.gui2.Panel
import com.googlecode.lanterna.gui2.WindowBasedTextGUI

class Menu extends ScreenComponent {
   MenuOption[] options
   Panel mainPanel
   ActionListBox optionList
   
   def public Menu(MenuOption... options) {
      this.options = options
      
      this.mainPanel = new Panel().with { 
         it.layoutManager = new GridLayout(1)
         return it
      }
      
      this.optionList = new ActionListBox(new TerminalSize(50, options.size())).with { optionList ->
         this.options.each {
            optionList.addItem(it.name, it.action)
         }
         return optionList
      }
      
      this.mainPanel.addComponent(this.optionList)
      this.window.component = this.mainPanel
   }
}
