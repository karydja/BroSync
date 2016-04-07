package brosync.client.gui.components

import brosync.client.gui.pages.MainMenuPage;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;

class UploadBox extends ScreenComponent {
   Panel mainPanel
   TextBox filePath

   def public UploadBox() {
      this.filePath = new TextBox(new TerminalSize(40, 1))
      
      this.mainPanel = new Panel().with {
         it.layoutManager = new GridLayout(1)
         
         it.addComponent(this.filePath)
         it.addComponent(new EmptySpace())
         
         it.addComponent(new Panel().with { buttonsPanel ->
            buttonsPanel.layoutManager = new GridLayout(2)
            buttonsPanel.addComponent(new Button('Voltar', { MainMenuPage.instance.draw() }))
            buttonsPanel.addComponent(new Button('Upload', { MainMenuPage.instance.draw() }))
         })
      }
      
      this.window.component = this.mainPanel
   }
}
