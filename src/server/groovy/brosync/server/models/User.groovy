package brosync.server.models

import brosync.communications.params.Params

class User extends Model {
   Integer id
   String username
   String email
   String password
}
