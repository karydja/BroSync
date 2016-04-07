package brosync.server.db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository

import brosync.server.models.Sharing

@Repository
class SharingDAO {

   @Autowired
   JdbcTemplate jdbcTemplate

   def create(Sharing newSharing) {
      new SimpleJdbcInsert(jdbcTemplate)
         .withTableName('sharings')
         .usingGeneratedKeyColumns('id')
         .executeAndReturnKey(newSharing as Map)
   }
}
