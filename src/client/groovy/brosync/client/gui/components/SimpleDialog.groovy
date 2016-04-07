package brosync.client.gui.components

import org.springframework.aop.aspectj.RuntimeTestWalker.ThisInstanceOfResidueTestVisitor;

import brosync.client.gui.GUI;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;

class SimpleDialog extends ScreenComponent {
   Panel mainPanel
   Label message
   
   def public SimpleDialog(String message, Closure confirmAction = {GUI.popWindow()}) {
      this.mainPanel = new Panel().with {
         it.layoutManager = new GridLayout(1)
         return it
      }
      
      this.message = new Label(message).addTo(this.mainPanel)
      
      this.mainPanel.addComponent(new EmptySpace())
      this.mainPanel.addComponent(new Button('OK', confirmAction))
      
      this.window.component = this.mainPanel
   }
}
