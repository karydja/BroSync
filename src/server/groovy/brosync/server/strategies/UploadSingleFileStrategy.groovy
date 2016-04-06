package brosync.server.strategies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import brosync.communications.Reply
import brosync.communications.params.Params
import brosync.server.operations.FileOperationsService

@Component
@Scope('prototype')
class UploadSingleFileStrategy extends Strategy {
   @Autowired
   FileOperationsService fileOperations

   @Override
   def Reply executeRequestMethodAction(Params params) {
   }
}
