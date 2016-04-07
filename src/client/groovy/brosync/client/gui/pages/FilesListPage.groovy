package brosync.client.gui.pages

import brosync.client.auth.Session;
import brosync.client.gui.components.FileList;
import brosync.client.gui.components.UserForm;
import groovy.lang.Delegate;
import groovy.lang.Singleton;

@Singleton
class FilesListPage {

   FileList list = new FileList(
      '/home/karydja/zsh-config/solarized-console.sh',
      '/home/karydja/zsh-config/README.md'
   )
   
   def public void draw() {
      Session.authorize(list)
   }
}