package brosync.client.connect

import brosync.client.Props;
import brosync.communications.Reply
import brosync.communications.Request

class Connect {

   def static Reply sendRequest(Request request) {
      def reply
      
      new Socket(Props.client.serverHost, Props.client.serverPort).withObjectStreams { input, output ->
         output.writeObject(request)
         reply = input.readObject()
      }
      
      return reply
   }
}
