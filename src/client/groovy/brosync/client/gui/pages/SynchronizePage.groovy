package brosync.client.gui.pages

import brosync.client.auth.Session;
import brosync.client.gui.components.PendingActionDialog;
import brosync.client.gui.components.UserForm;
import groovy.lang.Delegate;
import groovy.lang.Singleton;

@Singleton
class SynchronizePage {
   
   PendingActionDialog dialog = new PendingActionDialog(
      'Sincronização em andamento. Aguarde.',
      { dialog ->
         // upload logic
         Thread.sleep(5000)
         MainMenuPage.instance.draw()
      }
   )
   
   def public void draw() {
      Session.authorize(dialog)
   }
}
