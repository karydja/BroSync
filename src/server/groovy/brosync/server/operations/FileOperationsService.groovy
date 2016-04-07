package brosync.server.operations

import java.nio.file.Files
import java.nio.file.Paths

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils

import brosync.communications.dto.File as FileDTO
import brosync.server.Application
import brosync.server.db.FileDAO
import brosync.server.db.SharingDAO
import brosync.server.db.UserDAO
import brosync.server.models.File as FileModel
import brosync.server.models.Sharing

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

   def public List getUsersWithFileAlreadyShared(String username, String path, boolean isDirectory = false) {
      if(isDirectory) {
         userDAO.findUsernamesOfExistingSharingsForDirectoryByPathAndUsername(username, path)
      } else {
         userDAO.findUsernamesOfExistingSharingsForFileByPathAndUsername(username, path)
      }
   }

   def public update(String username, FileDTO file) {
      fileDAO.updateTimestampByPath(file.timestamp, file.path, username)
      
      def fileId = fileDAO.findByPathAndUsername(file.path, username).id
      def storagePath = Paths.get(Application['server.storage:BroStorage'])
      storagePath.resolve(fileId as String).bytes = file.data
   }

   def public create(String username, String[] usernames, FileDTO file) {
      def fileModel = fileDTOToFileModel(file)
      def fileId = fileDAO.create(fileModel)

      (usernames + username).each {
         def user = userDAO.findByUsername(it)
         def sharing = new Sharing([
            user_id: user.id,
            file_id: fileId
         ])

         sharingDAO.create(sharing)
      }

      def storagePath = Paths.get(Application['server.storage:BroStorage'])
      storagePath.resolve(fileId as String).bytes = file.data
   }
   
   def getStorageFiles(username, File[] files) {
      def storagePath = Paths.get(Application['server.storage:BroStorage'])
   }
   
   def getUserFiles(String username) {
      fileDAO.findByUsername(username)
   }

   def FileModel fileDTOToFileModel(FileDTO file) {
      def tempfile = Paths.get(file.path)

      new FileModel([
         original_dir: tempfile.parent == null ? null : "${tempfile.parent}${File.separator}",
         original_path_within_original_dir: tempfile.fileName,
         newest_timestamp: file.timestamp
      ])
   }
}
