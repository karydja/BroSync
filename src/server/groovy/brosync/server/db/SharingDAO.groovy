package brosync.server.db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class SharingDAO {

   @Autowired
   JdbcTemplate jdbcTemplate
}
