package brosync.server.strategies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import brosync.communications.Reply
import brosync.communications.params.Params
import brosync.server.operations.FileOperationsService

@Component
@Scope('prototype')
class SyncStrategy extends Strategy {
   @Autowired
   FileOperationsService fileOperations
   
   @Override
   def Reply executeRequestMethodAction(Params params) {
      def allFiles = fileOperations.getUserFiles(params.username)
      def syncFiles = params.files.collect {
         fileOperations.fileDTOToFileModel(it)         
      }
      
      allFiles.findAll {
         def existingFile = syncFiles.find { synchronizedFile ->
            synchronizedFile.original_dir == it.original_dir && 
               synchronizedFile.original_path_within_original_dir == it.original_path_within_original_dir
         }
         
         if (existingFile) {
            if (existingFile.newest_timestamp < it.newest_timestamp) {
               return true
            }
         } else {
            return true
         }
         
         return false
      }
      
      
      /*
       * recebe usuário
       * pegar todos os files destinados àquele usuário
       */
      return null
   }
}
