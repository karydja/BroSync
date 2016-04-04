package brosync.server.strategies

import brosync.communications.Reply
import brosync.communications.ReplyStatus
import brosync.communications.params.Params

abstract class Strategy implements Runnable {
   Params params
   Socket socket

   def abstract Reply executeRequestMethodAction(Params params)

   @Override
   def void run() {
      def reply = null

      try {
         if (!socket) {
            throw NullPointerException('Socket not connected.')
         }

         reply = executeRequestMethodAction(params)
      } catch(Exception ex) {
         reply = new Reply(
            status: ReplyStatus.DATABASE_ERROR,
            message: 'Username ou Email em uso. Mude os valores e tente novamente.'
         )
      } finally {
         if(socket) {
            def output = new ObjectOutputStream(socket.outputStream)
            output.writeObject(reply)
            socket.close()
         }
      }
   }
}
