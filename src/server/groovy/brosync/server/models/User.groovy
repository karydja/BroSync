package brosync.server.models

import brosync.communications.params.Params

class User {
   Integer id
   String username
   String email
   String password

   def public User(Params params) {
      username = params.username
      email = params.email
      password = params.password
   }
}
