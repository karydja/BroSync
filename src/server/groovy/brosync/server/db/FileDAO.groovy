package brosync.server.db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class FileDAO {
   @Autowired
   JdbcTemplate jdbcTemplate

   def create(File newFile) {
      def sql = """
         INSERT INTO files (
            id,
            original_dir,
            original_path_within_original_dir,
            newest_timestamp,
            filename
         ) VALUES (DEFAULT, ?, ?, ?, ?)
      """
      jdbcTemplate.update(sql,
         newFile.original_dir,
         newFile.original_path_within_original_dir,
         newFile.newest_timestamp,
         newFile.filename
      )
   }
}
