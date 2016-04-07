package brosync.client.gui.components

import org.apache.commons.lang3.StringUtils;

import groovy.lang.Closure;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;

class UserList extends ScreenComponent {
   TextBox users
   Panel mainPanel
   
   def public UserList(String currentUserMessage, Closure callback) {
      this.mainPanel = new Panel().with {
         it.layoutManager = new GridLayout(1)
         
         it.addComponent(new Label(currentUserMessage))
         it.addComponent(new EmptySpace())
         
         it.addComponent(new Label('Digite uma lista de usuários separada por vírgula'))
         this.users = new TextBox().addTo(it)
         
         it.addComponent(new EmptySpace())
         
         it.addComponent(new Button('Confirmar', { callback(this.users.text.split(',').collect(StringUtils.&trim))} ))
      }
      
      this.window.component = this.mainPanel
   }
}
