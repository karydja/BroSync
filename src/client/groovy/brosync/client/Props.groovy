package brosync.client

import java.nio.file.Paths;

class Props {
   static client = new ConfigSlurper().parse(Paths.get('client.groovy').toUri().toURL())
}
