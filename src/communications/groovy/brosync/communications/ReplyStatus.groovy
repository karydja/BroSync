package brosync.communications

enum ReplyStatus {
   OK(definition: 'The command was processed successfully.'),
   ERROR(definition: 'The command just failed due to some error.'),
   DATABASE_ERROR(definition: 'The command failed because of a database-level error.'),
   NOT_FOUND(definition: 'The resource was not found.'),
   NOT_UPDATED(definition: 'The file/path was not updated.')

   String definition

   @Override
   def String toString() {
      "${this.name()}: ${this.definition}"
   }
}
