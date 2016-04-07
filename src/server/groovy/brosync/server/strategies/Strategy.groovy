package brosync.server.strategies

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import brosync.communications.Reply
import brosync.communications.ReplyStatus
import brosync.communications.params.Params

abstract class Strategy implements Runnable {
   Params params
   Socket socket
   private static final Logger logger = LoggerFactory.getLogger(Strategy)

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
         logger.error('', ex)

         reply = new Reply(
            status: ReplyStatus.ERROR,
            message: 'Aconteceu algum erro. Por favor, repita a operação.'
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
