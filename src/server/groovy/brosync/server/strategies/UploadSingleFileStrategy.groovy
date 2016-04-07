package brosync.server.strategies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import brosync.communications.Reply
import brosync.communications.ReplyStatus
import brosync.communications.params.Params
import brosync.server.db.FileDAO
import brosync.server.operations.FileOperationsService

@Component
@Scope('prototype')
class UploadSingleFileStrategy extends Strategy {
   @Autowired
   FileDAO fileDAO

   @Autowired
   FileOperationsService fileOperations

   @Override
   def Reply executeRequestMethodAction(Params params) {
      if(params.existingPath) {
         def fileTimestamp = fileDAO.selectTimestamp(params.attachment.path, params.username)

         // Caso 1.1
         if(fileTimestamp == params.attachment.timestamp) {
            new Reply(
               status: ReplyStatus.NOT_UPDATED,
               message: """
                  Este arquivo não precisa ser atualizado.
               """
            )
         } else if(fileTimestamp < params.attachment.timestamp) {
            fileOperations.update(params.username, params.attachment)

            new Reply(
               status: ReplyStatus.OK,
               message: """
                  Arquivo atualizado com sucesso.
               """
            )
         } else {            
            new Reply(
               status: ReplyStatus.NOT_UPDATED,
               message: """
                  Faça download da versão mais atualizada desse arquivo.
               """
            )
         }
      } else {
         // Caso 1.2
         fileOperations.create(params.username, params.usernames, params.attachment)

         new Reply(
            status: ReplyStatus.OK,
            message: """
               Arquivo compartilhado com sucesso.
            """
         )
      }
   }
}
