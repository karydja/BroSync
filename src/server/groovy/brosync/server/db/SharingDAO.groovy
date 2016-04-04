package brosync.server.db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class SharingDAO {

   @Autowired
   JdbcTemplate jdbcTemplate

   def List findSharingsForFileByPathAndUsername(String username, String filePath) {
      this.findByCondition(username, path_for_user: filePath)
   }

   def List findSharingsForDirectoryByPathAndUsername(String username, String filePath) {
      this.findByCondition(username, dir_path_for_user: filePath)
   }

   def private List findByCondition(String username, Map condition) {
      def column = condition.keySet().iterator().next()
      def value = condition.entrySet().iterator().next().value

      def sql = """
         SELECT * FROM sharings
         INNER JOIN users
         ON users.id = sharings.user_id
         WHERE ${column} = ?
         AND users.username = ?
      """

      jdbcTemplate.query(sql, new BeanPropertyRowMapper(Sharing), value, username)
   }
}
