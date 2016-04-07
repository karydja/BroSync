package brosync.communications

import org.apache.commons.lang3.text.WordUtils

enum RequestMethod {
   SIGN_UP,
   LOGIN,
   CHECK_SINGLE_FILE,
   UPLOAD_SINGLE_FILE,
   CHECK_DIRECTORY,
   UPLOAD_DIRECTORY,
   SYNC

   def getCamelCaseName() {
      WordUtils.capitalizeFully(this.name(), '_' as char).replaceAll('_', '')
   }
}
