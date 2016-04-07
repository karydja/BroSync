package brosync.server.models

import java.sql.Timestamp

class File extends Model {
   Integer id
   String original_dir
   String original_path_within_original_dir
   Long newest_timestamp
}
