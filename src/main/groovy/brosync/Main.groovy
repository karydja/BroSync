package brosync

import org.springframework.context.ApplicationContext
import org.springframework.context.support.GenericGroovyApplicationContext
import brosync.app.Classe

class Main {
  public static void main(String... args) {
     ApplicationContext context = new GenericGroovyApplicationContext('classpath:config.groovy')

     def c = context.getBean(Classe)
     c.metodo()
  }
}
