package brosync.server

import java.nio.file.Files
import java.nio.file.Paths

import org.springframework.context.ApplicationContext
import org.springframework.context.support.GenericGroovyApplicationContext

class Application {
   static ApplicationContext context

   public static void main(String... args) {
      def context = new GenericGroovyApplicationContext('classpath:config.groovy')
      this.context = context

      def storagePath = Paths.get(Application['server.storage:BroStorage'])
      if(Files.notExists(storagePath)) {
         Files.createDirectory(storagePath)
      }

      Server.start()
   }

   def static String getAt(String property) {
      context.getBeanFactory().resolveEmbeddedValue("\${${property}}")
   }
}
