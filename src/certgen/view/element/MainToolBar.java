package certgen.view.element;

import javax.swing.JToolBar;

import certgen.controller.action.ActionManager;

/**
 * Represents main tool bar.
 * 
 * @author Dražen Đanić
 */
public class MainToolBar extends JToolBar {

	private static final long serialVersionUID = 4280673262160009694L;
	
	public MainToolBar(ActionManager actionManager) {
		setFocusable(false);
		
		
		add(actionManager.getNewKeyStoreAction()).setFocusable(false);
		add(actionManager.getOpenKeyStoreAction()).setFocusable(false);
		add(actionManager.getSaveKeyStoreAction()).setFocusable(false);
		addSeparator();
		add(actionManager.getNewCertificateAction()).setFocusable(false);
		add(actionManager.getExportCertificateAction()).setFocusable(false);
	}

}
