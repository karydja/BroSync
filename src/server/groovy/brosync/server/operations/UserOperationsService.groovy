package brosync.server.operations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import brosync.server.db.UserDAO
import brosync.server.models.User

@Service
class UserOperationsService {
   @Autowired
   UserDAO userDAO

   def signUp(User newUser) {
      userDAO.create(newUser)
   }
}
