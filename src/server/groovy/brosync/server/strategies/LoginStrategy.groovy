package brosync.server.strategies

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import brosync.communications.Reply
import brosync.communications.params.Params

@Component
@Scope('prototype')
class LoginStrategy extends Strategy {

   @Override
   def Reply executeRequestMethodAction(Params params) {
      return null;
   }
}
