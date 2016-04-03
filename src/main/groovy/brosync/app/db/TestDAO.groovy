package brosync.app.db

import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate

@Repository
class TestDAO {
  @Autowired
  JdbcTemplate jdbcTemplate

  def test() {
    def retorno = jdbcTemplate.queryForObject('SELECT 1', Integer)  
    println retorno == 1
  }
}
