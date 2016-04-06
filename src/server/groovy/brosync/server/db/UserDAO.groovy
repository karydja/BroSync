package brosync.server.db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
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
   
   def findByUsernameList(String[] usernames) {
      def sql = """
         SELECT * FROM users WHERE username IN ?
      """
      jdbcTemplate.query(sql, new BeanPropertyRowMapper(User), usernames)
   }

   def List findUsernamesOfExistingSharingsForFileByPathAndUsername(String username, String filePath) {
      this.findUsernamesOfExistingSharingsByCondition(username, [path_for_user: filePath])
   }

   def List findUsernamesOfExistingSharingsForDirectoryByPathAndUsername(String username, String filePath) {
      this.findUsernamesOfExistingSharingsByCondition(username, [dir_path_for_user: filePath])
   }

   def private List findUsernamesOfExistingSharingsByCondition(String username, Map condition) {
      def column = condition.keySet().iterator().next()
      def value = condition.entrySet().iterator().next().value

      def sql = """
         SELECT allusers.username FROM users allusers

         INNER JOIN sharings allsharings
         ON allusers.id = allsharings.user_id

         INNER JOIN files
         ON files.id = allsharings.file_id

         INNER JOIN sharings existingsharing
         ON existingsharing.file_id = files.id

         INNER JOIN users requestuser
         ON requestuser.id = existingsharing.user_id

         WHERE existingsharing.${column} = ?
         AND requestuser.username = ?
      """

      jdbcTemplate.queryForList(sql, String, value, username)
   }
}
