package brosync.server.db
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository

import brosync.server.models.File

@Repository
class FileDAO {
   @Autowired
   JdbcTemplate jdbcTemplate

   def create(File newFile) {
      new SimpleJdbcInsert(jdbcTemplate)
         .withTableName('files')
         .usingGeneratedKeyColumns('id')
         .executeAndReturnKey(newFile as Map)
   }

   def selectTimestamp(String path, String username) {
      def sql = """
         SELECT newest_timestamp from files
         INNER JOIN sharings
         ON sharings.file_id = files.id
         INNER JOIN users
         ON sharings.user_id = users.id

         WHERE users.username = ?
         AND CONCAT(COALESCE(files.original_dir, ''), files.original_path_within_original_dir) = ?
      """
      
      jdbcTemplate.queryForObject(sql, Long, username, path)
   }
   
   def updateTimestamp(File file) {
      def sql = """
         UPDATE files SET newest_timestamp = ?        
      """

      jdbcTemplate.update(sql, file.newest_timestamp)
   }

   def updateTimestampByPath(Long timestamp, String path, String username) {
      def sql = """
         UPDATE files SET newest_timestamp = ?
         FROM sharings, users

         WHERE sharings.file_id = files.id
         AND sharings.user_id = users.id
         AND users.username = ?
         AND CONCAT(COALESCE(files.original_dir, ''), files.original_path_within_original_dir) = ?
      """
      
      jdbcTemplate.update(sql, timestamp, username, path)
   }
   
   def findByPathAndUsername(String path, String username) {
      def sql = """
         SELECT * from files
         INNER JOIN sharings
         ON sharings.file_id = files.id
         INNER JOIN users
         ON sharings.user_id = users.id

         WHERE users.username = ?
         AND CONCAT(COALESCE(files.original_dir, ''), files.original_path_within_original_dir) = ?
      """
   
      def results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(File), username, path)
      
      if(!results.empty) {
         results[0]
      } else {
         null
      }
   }
   
   def findByUsername(String username) {
      def sql = """
         SELECT * from files
         INNER JOIN sharings
         ON sharings.file_id = files.id
         INNER JOIN users
         ON sharings.user_id = users.id

         WHERE users.username = ?
      """

      def results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(File), username)
   }
}
