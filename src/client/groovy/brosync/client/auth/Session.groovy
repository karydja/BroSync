package brosync.client.auth

import brosync.client.gui.GUI;
import brosync.client.gui.components.ScreenComponent
import brosync.client.gui.components.SimpleDialog;
import brosync.client.gui.components.UserForm;
import brosync.client.gui.pages.LoginFormPage
import brosync.client.gui.pages.MainMenuPage;

class Session {
   static String username
   static String token
   
   static void authorize(ScreenComponent screen = null) {
      screen = screen ?: MainMenuPage.instance.menu
      
      if (!Session.username || !Session.token) {
         def loginForm = new UserForm({ user, pass ->
            // create request
            // check reply
            // error out if not authorized
            
            // GUI.pushWindow(new SimpleDialog(reply.message, { MainMenuPage.instance.draw() }).window)
            
            // write token to file if authorized
            // populate username and token vars
            username = user
            token = '123'   
            // go to authorized screen component
            
            def reply = 'Autorizado' // temp
            GUI.pushWindow(new SimpleDialog(reply, { screen.draw() }).window)
         })
         
         GUI.pushWindow(new SimpleDialog('Autentique-se primeiro!', { loginForm.draw() }).window)
      } else {
         screen.draw()
      }
   }
}
