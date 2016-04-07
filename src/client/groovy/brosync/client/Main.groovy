package brosync.client

import brosync.client.gui.pages.MainMenuPage

class Main {
   static void main(String... args) {
      // MainMenuPage.instance.draw()
      def broPath = Paths.get('/home/karydja/Desktop/BroSync/')
      if(Files.notExists(broPath)) {
         Files.createDirectory(broPath)
      }
            
      def socket = new Socket('localhost', 4567)
      def output = new ObjectOutputStream(socket.outputStream)


      // REQUEST #1 PARA ARQUIVOS SOLTOS
      /*def params = new CheckSingleFileParams([
         username: 'karydja',
         path: 'a.txt',
         isDirectory: false
      ])
      def request = new Request(
         method: RequestMethod.CHECK_SINGLE_FILE,
         params: params
      )*/


      // REQUEST #2 PARA ARQUIVOS SOLTOS
      /*def file = Paths.get("/home/karydja/Desktop/testando.txt")
      def fileDTO = new File([
         path: 'testando2.txt',
         timestamp: Files.getLastModifiedTime(file).toMillis(),
         data: file.bytes
      ])
      def params = new UploadSingleFileParams([
         username: 'karydja',
         usernames: ['mateus', 'ronaldo'],
         existingPath: true, // existingPath vem do Reply #1
         attachment: fileDTO
      ])
      def request = new Request(
         method: RequestMethod.UPLOAD_SINGLE_FILE,
         params: params
      )*/

      
      // DOWNLOAD DE ARQUIVOS SOLTOS
      def files = [].with { files ->
         broPath.eachFileRecurse(FileType.FILES) {
            files << new File(
               path: broPath.relativize(it),
               timestamp: Files.getLastModifiedTime(it).toMillis(),
               data: null
            )
         }
         
         return files
      }      
      def params = new SyncParams([
         username: 'karydja',
         files: files
      ])
      def request = new Request(
         method: RequestMethod.SYNC,
         params: params
      )


      // REQUEST #1 PARA DIRETÓRIOS
      /*def params = new CheckDirectoryParams([
         username: 'karydja',
         path: 'Fotos',
         isDirectory: true
      ])
      def request = new Request(
         method: RequestMethod.CHECK_DIRECTORY,
         params: params
      )*/


      // REQUEST PARA SIGN UP
      /*def params = new SignUpParams([
         username: 'karydja6',
         email: 'karydja6@teste.com',
         password: 'password'
      ])
      def request = new Request(
         method: RequestMethod.SIGN_UP,
         params: params
      )*/

      output.writeObject(request)

      def input = new ObjectInputStream(socket.inputStream)
      def reply = input.readObject()

      println reply.status
      println reply.message
   }
}
