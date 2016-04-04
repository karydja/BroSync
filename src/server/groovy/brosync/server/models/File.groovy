package brosync.server.models

import java.sql.Timestamp

class File {
   int id
   String original_dir
   String original_path_within_original_dir
   Timestamp newest_timestamp
   String filename
}
