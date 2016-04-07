package brosync.client.gui.components

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;

class UserForm extends ScreenComponent {
   TextBox user
   TextBox password
   Panel mainPanel
   
   def public UserForm(Closure confirmationAction) {
      this.mainPanel = new Panel().with { 
         it.layoutManager = new GridLayout(2)
         
         it.addComponent(new Label('Usuário: '))
         this.user = new TextBox().addTo(it)
         
         it.addComponent(new Label('Senha: '))
         this.password = new TextBox().addTo(it)
         
         // The ideal way should be to use the TerminalSize constructor
         // but that didn't work correctly for some reason
         it.addComponent(new EmptySpace())
         it.addComponent(new EmptySpace())
         it.addComponent(new EmptySpace())
         it.addComponent(new Button('Confirmar', { confirmationAction(this.user, this.password) }))
      }
      
      this.window.component = this.mainPanel
   }
}
