package brosync.client.gui.pages

import brosync.client.gui.components.ScreenComponent;
import brosync.client.gui.components.menu.Menu;
import brosync.client.gui.components.menu.MenuOption;

@Singleton
class MainMenuPage {
   
   @Delegate
   Menu menu = new Menu(
      new MenuOption(
         name: 'Cadastrar-se',
         action: { RegisterFormPage.instance.draw() }
      ),
      new MenuOption(
         name: 'Listar arquivos disponíveis para sincronização',
         action: { FilesListPage.instance.draw() }
      ),
      new MenuOption(
         name: 'Upload de diretório ou arquivo',
         action: { UploadPage.instance.draw() }
      ),
      new MenuOption(
         name: 'Sincronizar',
         action: { SynchronizePage.instance.draw() }
      ),
//      new MenuOption(
//         name: 'Download de arquivos ou diretórios específicos',
//         action: { SecondaryMenuPage.instance.draw() }
//      )
   )
}
