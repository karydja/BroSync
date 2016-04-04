package brosync.server

import org.springframework.context.ApplicationContext
import org.springframework.context.support.GenericGroovyApplicationContext

import brosync.communications.Request

class Application {
   static ApplicationContext context

   public static void main(String... args) {
      def context = new GenericGroovyApplicationContext('classpath:config.groovy')
      this.context = context
      Server.start()
   }

   def static String getAt(String property) {
      context.getBeanFactory().resolveEmbeddedValue("\${${property}}")
   }
}
