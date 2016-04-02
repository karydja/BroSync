package brosync

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import brosync.config.ContextConfig
import brosync.app.db.TestDAO
import brosync.app.Classe

class Main {
  public static void main(String... args) {
     ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfig.class)

     def c = context.getBean(Classe)
  }
}
