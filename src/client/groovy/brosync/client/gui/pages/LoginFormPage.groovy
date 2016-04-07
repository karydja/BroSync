package brosync.client.gui.pages

import brosync.client.gui.components.UserForm;
import groovy.lang.Delegate;
import groovy.lang.Singleton;

@Singleton
class LoginFormPage {

   UserForm form = new UserForm({ user, pass ->
      // login logic
      MainMenuPage.instance.draw()
   })
   
   def public void draw(previousScreen = null) {
      this.form.draw(previousScreen)
   }
}
