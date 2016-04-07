package brosync.client.gui.components

import brosync.client.gui.GUI;

import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextGUIGraphics

class PendingActionDialog extends ScreenComponent {
   Panel mainPanel
   Label message
   Closure action
   
   def public PendingActionDialog(String initialMessage, Closure action) {
      this.action = action
      def _this = this
      
      this.mainPanel = new Panel().with {
         it.layoutManager = new GridLayout(1)
         return it
      }
      
      this.message = new Label(initialMessage)
      

      
      this.message.addTo(this.mainPanel)
      this.window.component = this.mainPanel
   }
   
   def public void draw() {
      super.draw(false)
      this.action(this)
   }
}
