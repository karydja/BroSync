package brosync.client.gui.pages

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.Path

import brosync.client.Props
import brosync.client.auth.Session
import brosync.client.connect.Connect
import brosync.client.gui.GUI
import brosync.client.gui.components.FileList
import brosync.client.gui.components.SimpleDialog
import brosync.client.gui.components.UploadBox
import brosync.client.gui.components.UserList
import brosync.communications.dto.File
import brosync.communications.ReplyStatus
import brosync.communications.Request
import brosync.communications.RequestMethod
import brosync.communications.params.CheckSingleFileParams
import brosync.communications.params.UploadSingleFileParams
import groovy.lang.Delegate
import groovy.lang.Singleton

@Singleton
class UploadPage {

<<<<<<< HEAD
   @Delegate
=======
>>>>>>> 76843f1... Temp commit
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
         GUI.pushWindow(new SimpleDialog(reply.message, { MainMenuPage.instance.draw() }).window )
      }
      
      new UserList(reply.message, { users ->
         def uploadParams = new UploadSingleFileParams(
            username: Session.username,
            usernames: users,
            existingPath: reply.attachment.existingPath,
            attachment: new File(
               path: broPath.relativize(filePath),
               timestamp: Files.getLastModifiedTime(filePath).toMillis(),
               data: filePath.bytes
            )
         )
         
         def uploadRequest = new Request(
            method: RequestMethod.UPLOAD_SINGLE_FILE,
            params: params
         )
         
         def uploadReply = Connect.sendRequest(uploadRequest)
         
         GUI.pushWindow(new SimpleDialog(reply.message, { MainMenuPage.instance.draw() }).window )
      })
   })
   
   def public void draw() {
      Session.authorize(box)
   }
}
