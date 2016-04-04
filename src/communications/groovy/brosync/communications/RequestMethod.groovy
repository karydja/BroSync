package brosync.communications

import org.apache.commons.lang3.text.WordUtils

enum RequestMethod {
   SIGN_UP,
   LOGIN

   def getCamelCaseName() {
      WordUtils.capitalizeFully(this.name(), '_' as char).replaceAll('_', '')
   }
}
