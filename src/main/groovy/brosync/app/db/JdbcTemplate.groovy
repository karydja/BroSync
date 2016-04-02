package brosync.app.db

import brosync.app.db.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate as SpringJdbcTemplate
import org.springframework.stereotype.Component

@Component
class JdbcTemplate extends SpringJdbcTemplate {
  @Autowired
  DataSource dataSource
}
