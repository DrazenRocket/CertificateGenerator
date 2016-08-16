package certgen.view.element;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import certgen.controller.action.ActionManager;

/**
 * Represents main menu bar.
 * 
 * @author Dražen Đanić
 */
public class MainMenuBar extends JMenuBar {

	private static final long serialVersionUID = 4932340045398343274L;
	
	public MainMenuBar(ActionManager actionManager) {
		JMenu mnuFile = new JMenu("File");
		mnuFile.setMnemonic('F');
		mnuFile.add(actionManager.getNewKeyStoreAction());
		mnuFile.add(actionManager.getOpenKeyStoreAction());
		mnuFile.addSeparator();
		mnuFile.add(actionManager.getSaveKeyStoreAction());
		mnuFile.add(actionManager.getSaveAsKeyStoreAction());
		mnuFile.addSeparator();
		mnuFile.add(actionManager.getExitApplicationAction());
		add(mnuFile);
		
		JMenu mnuTools = new JMenu("Tools");
		mnuTools.setMnemonic('T');
		mnuTools.add(actionManager.getNewCertificateAction());
		mnuTools.add(actionManager.getExportCertificateAction());
		add(mnuTools);
	}

}
