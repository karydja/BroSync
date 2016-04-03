package brosync.server

import brosync.server.db.TestDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Classe {
   @Autowired
   TestDAO testDAO

   def metodo() {
      println testDAO.test()
   }
}
