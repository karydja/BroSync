import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.jdbc.datasource.DriverManagerDataSource

beans {
   xmlns([context: 'http://www.springframework.org/schema/context'])
   context.'component-scan'('base-package': 'brosync.server')
   //context.'property-placeholder'('location': 'file:server.properties', 'ignore-resource-not-found': true)
   context.'property-placeholder'('location': 'file:database.properties')

   // beans
   dataSource (DriverManagerDataSource) {
      url = '${database.url}'
      username = '${database.user}'
      password = '${database.pass}'
   }

   jdbcTemplate (JdbcTemplate) {
      dataSource = dataSource   
   }
}
