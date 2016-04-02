package brosync.app.db

import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:database.properties")
class DataSource extends DriverManagerDataSource {
  @Value('jdbc:postgresql://localhost/file_sync')
  String url

  @Value('doodad')
  String username

  @Value('doodad')
  String password
}
