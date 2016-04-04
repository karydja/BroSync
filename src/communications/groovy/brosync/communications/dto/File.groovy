package brosync.communications.dto

import java.nio.file.attribute.FileTime

class File implements Serializable {
   String systemPath
   FileTime timestamp
   byte[] data
}
