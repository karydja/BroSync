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
class SyncStrategy extends Strategy {
   @Autowired
   FileOperationsService fileOperations
   
   @Override
   def Reply executeRequestMethodAction(Params params) {
      def allFiles = fileOperations.getUserFiles(params.username)
      def syncFiles = params.files.collect {
         fileOperations.fileDTOToFileModel(it)         
      }
      
      def downloadFiles = allFiles.findAll {
         def existingFile = syncFiles.find { synchronizedFile ->
            synchronizedFile.original_dir == it.original_dir && 
               synchronizedFile.original_path_within_original_dir == it.original_path_within_original_dir
         }
         
         if (existingFile) {
            if (existingFile.newest_timestamp < it.newest_timestamp) {
               downloadFiles << it
               return false
            }
         } else {
            return true
         }
         
         return false
      }

      if(downloadFiles.empty) {
         return new Reply (
            status: ReplyStatus.NOT_FOUND,
            message: "Nao há arquivos a serem sincronizados."
         )
      } else {
         fileOperations.getStorageFiles(username, downloadFiles)

         return new Reply (
            status: ReplyStatus.OK,
            message: "A sincronização foi realizada com sucesso."
         )
      }
   }
}
