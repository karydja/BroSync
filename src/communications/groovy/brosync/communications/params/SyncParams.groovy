package brosync.communications.params

import brosync.communications.dto.File

class SyncParams implements Params {
   String username
   File[] files
}
