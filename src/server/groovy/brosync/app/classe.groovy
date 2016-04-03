package brosync.app

import brosync.app.db.TestDAO
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
