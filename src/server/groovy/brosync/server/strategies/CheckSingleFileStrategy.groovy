package brosync.server.strategies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import brosync.communications.Reply
import brosync.communications.params.Params
import brosync.server.operations.FileOperationsService

@Component
@Scope('prototype')
class CheckSingleFileStrategy extends Strategy {
   @Autowired
   FileOperationsService fileOperations

   @Override
   def Reply executeRequestMethodAction(Params params) {
      /*
       * existem Sharings com o user sendo o mesmo da requisição e com
       * o path_for_user igual ao nome do arquivo?
       */
      def pathExists = fileOperations.checkPathExistence(
         params.username,
         params.path,
         params.isDirectory
      )

      if(pathExists) {
         // Caso 1.1
      } else {
         // Caso 1.2
         def reply = new Reply(
            status: ReplyStatus.NOT_FOUND,
            message: """
               Esse arquivo nunca foi sincronizado.

               Com quem deseja sincronizá-lo?
            """
         )
      }
   }
}
