package brosync.server.operations

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils

import brosync.communications.Reply
import brosync.communications.dto.File as FileDTO
import brosync.server.Application
import brosync.server.db.FileDAO
import brosync.server.db.SharingDAO
import brosync.server.db.UserDAO
import brosync.server.models.File as FileModel

@Service
class FileOperationsService {

   @Autowired
   FileDAO fileDAO

   @Autowired
   UserDAO userDAO

   @Autowired
   SharingDAO sharingDAO

   // chosenPath = path que vem do params do Request
   def synchronized upload(String chosenPath, FileDTO[] newFiles, String[] usernames) {
      def storagePath = Files.createDirectory(Paths.get(Application['server.storage:BroStorage']))

      def users = userDAO.findUsersByUsernameList(usernames)

      users.each { 
         def userDir = Files.createDirectory(storagePath.resolve(it.username)) 
         
         newFiles.each {
            def md5 = DigestUtils.md5Digest(it.data)
            def filename = calculateFileVersion(storagePath.resolve(md5))
         }
      }

      newFiles.each {
         // criar pasta com nome do usuário (caso já não existe) dentro de storagePath
         // criar md5 para o arquivo
         // testar se existe algum arquivo com esse md5
           // se já existir, incrementa um caractere no final e testa novamente
           // se não existir, usa o md5 como nome do arquivo
         // salvar FileModel no banco com o filename criado a partir do md5
         // iterar os userIds a fim de criar Sharings para cada arquivo
         def newFile = fileDTOToFileModel(it)

         fileDAO.create(newFile)
         // sharingDAO.create pra cada userId
      }
   }

   def public boolean checkPathExistence(String username, String path, boolean isDirectory = false) {
      def sharings

      if(isDirectory) {
         sharings = sharingDAO.findSharingsForDirectoryByPathAndUsername(username, path)
      } else {
         sharings = sharingDAO.findSharingsForFileByPathAndUsername(username, path)
      }

      return !sharings.empty
   }

   def private FileModel fileDTOToFileModel(FileDTO fileDTO, String chosenPath) {

   }

   def private String calculateFileVersion(Path file) {
   }
}
