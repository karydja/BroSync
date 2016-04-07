package brosync.server.strategies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import brosync.communications.Reply
import brosync.communications.ReplyStatus
import brosync.communications.params.Params
import brosync.server.operations.FileOperationsService

@Component
@Scope('prototype')
class CheckDirectoryStrategy extends Strategy {
   @Autowired
   FileOperationsService fileOperations

   @Override
   def Reply executeRequestMethodAction(Params params) {
      def usernames = fileOperations.getUsersWithFileAlreadyShared(
         params.username,
         params.path,
         params.isDirectory
      )

      if(usernames.empty) {
         // Caso 2.2
         new Reply(
            status: ReplyStatus.NOT_FOUND,
            message: """
               Essa pasta nunca foi compartilhada.
               Com quem deseja compartilhá-la?
            """,
            attachment: [existingPath: false]
         )
      } else {
         // Caso 2.1
         new Reply(
            status: ReplyStatus.OK,
            message: """
               Essa pasta foi compartilhada com os seguintes usuários:
               ${usernames.join(', ')}
            """,
            attachment: [existingPath: true]
         )
      }
   }
}
