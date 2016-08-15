package certgen.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Extended AbstractAction class which performs necessary things for saving key store to file. 
 * 
 * @author Dražen Đanić
 */
public class SaveKeyStoreAction extends AbstractAction {

	private static final long serialVersionUID = -2445258261419807685L;
	
	public SaveKeyStoreAction() {
		putValue(NAME, "Save Keystore");
		putValue(SHORT_DESCRIPTION, "Save keystore to file");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
