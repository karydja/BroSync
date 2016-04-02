package brosync.app

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import brosync.app.db.TestDAO

@Component
class Classe {
  @Autowired
  TestDAO testDAO
  
  def metodo() {
    println testDAO.test()
  }
}
