package brosync.client.gui.pages

import java.nio.file.Paths;
import java.nio.file.Path;

import brosync.client.Props;
import brosync.client.auth.Session;
import brosync.client.connect.Connect;
import brosync.client.gui.GUI;
import brosync.client.gui.components.FileList;
import brosync.client.gui.components.SimpleDialog;
import brosync.client.gui.components.UploadBox;
import brosync.communications.ReplyStatus;
import brosync.communications.Request
import brosync.communications.RequestMethod;
import brosync.communications.params.CheckSingleFileParams
import groovy.lang.Delegate;
import groovy.lang.Singleton;

@Singleton
class UploadPage {

   @Delegate
   UploadBox box = new UploadBox({ file ->
      def broPath = Props.client.broPath
      def filePath = Paths.get(file)
      
      if (!filePath.startsWith(broPath)) {
         def oldFilePath = filePath
         filePath = broPath.resolve(oldFilePath.fileName)
         filePath.bytes = oldFilePath.bytes
      }
      
      def params = new CheckSingleFileParams([
         username: Session.username,
         path: broPath.relativize(filePath),
         isDirectory: false
      ])
      
      def request = new Request(
         method: RequestMethod.CHECK_SINGLE_FILE,
         params: params
      )
      
      def reply = Connect.sendRequest(request)
      
      if (reply.status != ReplyStatus.OK) {
         GUI.pushWindow(new SimpleDialog(reply.message, { MainMenuPage.instance.draw() }))
      }
   })
   
   def public void draw() {
      Session.authorize(box)
   }
}
