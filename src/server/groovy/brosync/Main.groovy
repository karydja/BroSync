package brosync

import org.springframework.context.support.GenericGroovyApplicationContext

import brosync.server.Classe
import brosync.server.db.TestDAO;

class Main {
   public static void main(String... args) {
      def context = new GenericGroovyApplicationContext('classpath:config.groovy')

      def c = context.getBean(Classe)
      c.metodo()

      println TestDAO.toString()
      new TestDAO().test3()
   }
}
