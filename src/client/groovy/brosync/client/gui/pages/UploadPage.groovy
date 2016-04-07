package brosync.client.gui.pages

import brosync.client.gui.components.FileList;
import brosync.client.gui.components.UploadBox;
import groovy.lang.Delegate;
import groovy.lang.Singleton;

@Singleton
class UploadPage {

   @Delegate
   UploadBox box = new UploadBox()
}
