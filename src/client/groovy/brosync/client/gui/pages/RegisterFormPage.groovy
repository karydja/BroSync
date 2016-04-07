package brosync.client.gui.pages

import brosync.client.gui.components.UserForm
import brosync.client.gui.components.menu.MenuOption;

@Singleton
class RegisterFormPage {
   
   @Delegate
   UserForm form = new UserForm({ user, pass ->
      // register logic
      MainMenuPage.instance.draw()
   })
}
