UPDATE files SET newest_timestamp = 1459969222998
         FROM sharings, users
         WHERE sharings.file_id = files.id
         AND sharings.user_id = users.id
         AND users.username = 'karydja'
         AND CONCAT(COALESCE(files.original_dir, ''), files.original_path_within_original_dir) = 'testando2.txt'
