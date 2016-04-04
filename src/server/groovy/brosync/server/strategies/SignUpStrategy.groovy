package brosync.server.strategies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Component

import brosync.communications.Reply
import brosync.communications.ReplyStatus
import brosync.server.models.User
import brosync.server.operations.UserOperationsService

@Component
@Scope('prototype')
class SignUpStrategy extends Strategy {
   @Autowired
   UserOperationsService userOperations

   @Override
   def Reply executeRequestMethodAction(Map params) {
      def newUser = new User(params)

      try {
         userOperations.signUp(newUser)
         return new Reply(
            status: ReplyStatus.OK,
            message: 'Usuário cadastrado com sucesso.'
         )
      } catch(DuplicateKeyException ex) {
         return new Reply(
            status: ReplyStatus.DATABASE_ERROR,
            message: 'Username ou Email em uso. Mude os valores e tente novamente.'
         )
      }
   }
}
