package brosync.client

import brosync.communications.Request
import brosync.communications.RequestMethod
import brosync.communications.params.CheckDirectoryParams
import brosync.communications.params.CheckSingleFileParams
import brosync.communications.params.SignUpParams

class Main {
   static void main(String... args) {
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


      // REQUEST #1 PARA DIRETÓRIOS
      def params = new CheckDirectoryParams([
         username: 'karydja',
         path: 'Fotos',
         isDirectory: true
      ])
      def request = new Request(
         method: RequestMethod.CHECK_DIRECTORY,
         params: params
      )


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
