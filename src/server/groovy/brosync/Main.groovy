package brosync

import org.springframework.context.support.GenericGroovyApplicationContext

import brosync.app.Classe
import brosync.app.db.TestDAO;

class Main {
  public static void main(String... args) {
     def context = new GenericGroovyApplicationContext('classpath:config.groovy')

     def c = context.getBean(Classe)
	 c.metodo()
	 
	 println TestDAO.toString()
	 new TestDAO().test3()
  }
}
