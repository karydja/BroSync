package brosync.communications

class Reply implements Serializable {
   ReplyStatus status
   String message
   Object attachment
}
