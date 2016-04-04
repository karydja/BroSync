package brosync.communications

import brosync.communications.params.Params

class Request implements Serializable {
   RequestMethod method
   Params params
   Object attachment
}
