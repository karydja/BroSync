package brosync.client

import brosync.communications.Request
import brosync.communications.RequestMethod

class Main {
   static void main(String... args) {
      def socket = new Socket('localhost', 4567)
      def output = new ObjectOutputStream(socket.outputStream)
      def request = new Request(
         method: RequestMethod.SIGN_UP,
         params: [username: 'karydja6', email: 'karydja6@teste.com', password: 'password']
      )
      output.writeObject(request)

      def input = new ObjectInputStream(socket.inputStream)
      def reply = input.readObject()

      println reply.status
      println reply.message
   }
}
