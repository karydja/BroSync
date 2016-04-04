package brosync.server.db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

import brosync.server.models.User

@Repository
class UserDAO {
   @Autowired
   JdbcTemplate jdbcTemplate

   def create(User newUser) {
      def sql = """
         INSERT INTO users (
            id,
            username,
            email,
            password
         ) VALUES (DEFAULT, ?, ?, ?)
      """
      jdbcTemplate.update(sql, newUser.username, newUser.email, newUser.password)
   }
}
