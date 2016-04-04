package brosync.server

import brosync.communications.Request

class Server {
   def static start() {
      def port = Application['server.port:4567']
      def server = new ServerSocket(port as int)

      while(true) {
         def connection = null
         def input = null

         try {
            connection = server.accept()
            input = new ObjectInputStream(connection.inputStream)

            def request = input.readObject()
            def strategy = Application.context.getBean(
               "brosync.server.strategies.${request.method.camelCaseName}Strategy" as Class
            )

            strategy.with {
               params = request.params
               socket = connection
            }

            new Thread(strategy).start()
         } catch(Exception ex) {
            if (connection) {
               // Reply object with generic error
               // connection?.close()
            }
         } finally {
            // input?.close()
         }
      }
   }
}