package brosync.client.gui.components

import groovy.lang.Closure;
import brosync.client.gui.pages.MainMenuPage;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.table.Table;

class FileList extends ScreenComponent {
   Panel mainPanel
   Table table
   
   def public FileList(String... paths) {
      this.table = new Table('').with {
         paths.each(it.tableModel.&addRow)
         return it
      }
            
      this.mainPanel = new Panel().with {
         it.layoutManager = new GridLayout(1)
         
         it.addComponent(this.table)
         it.addComponent(new EmptySpace())
         it.addComponent(new Button('Voltar', { MainMenuPage.instance.draw() }))
      }
      
      this.window.component = this.mainPanel
   }
}
