package brosync.app.db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class TestDAO {
	
  @Autowired
  JdbcTemplate jdbcTemplate

  def test() {
    def retorno = jdbcTemplate.queryForObject('SELECT 1', Integer)  
    println retorno == 1
  }
  
  def test2() {
	def retorno = 3
	return 3 + 2
  }
  
  def test3() {
	  return null
  }
}
