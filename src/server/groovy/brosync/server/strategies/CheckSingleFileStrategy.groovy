package brosync.server.strategies

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import brosync.communications.Reply
import brosync.communications.ReplyStatus
import brosync.communications.params.Params
import brosync.server.operations.FileOperationsService

@Component
@Scope('prototype')
class CheckSingleFileStrategy extends Strategy {
   @Autowired
   FileOperationsService fileOperations

   private static final Logger logger = LoggerFactory.getLogger(CheckSingleFileStrategy)

   @Override
   def Reply executeRequestMethodAction(Params params) {
      def usernames = fileOperations.getUsersWithFileAlreadyShared(
         params.username,
         params.path,
         params.isDirectory
      )

      if(usernames.empty) {
         // Caso 1.2
         new Reply(
            status: ReplyStatus.NOT_FOUND,
            message: """
               Esse arquivo nunca foi compartilhado.
               Com quem deseja compartilhá-lo?
            """,
            attachment: [existingPath: false]
         )
      } else {
         // Caso 1.1
         new Reply(
            status: ReplyStatus.OK,
            message: """
               Esse arquivo foi compartilhado com os seguintes usuários:
               ${usernames.join(', ')}
            """,
            attachment: [existingPath: true]
         )
      }
   }
}
