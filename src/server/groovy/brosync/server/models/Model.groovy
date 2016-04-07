package brosync.server.models

class Model {
   def public Object asType(Class clazz) {
      if (clazz == Map) {
         this.asMap().findAll { key, value ->
            value != null
         }
      }
   }
   
   def private Map asMap() {
      this.class.declaredFields.findAll { !it.synthetic }.collectEntries {
         [ (it.name):this."$it.name" ]
      }
   }
}
