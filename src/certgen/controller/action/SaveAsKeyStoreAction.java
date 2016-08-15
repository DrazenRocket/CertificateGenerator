package certgen.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Extended AbstractAction class which performs necessary things for new saving key store to file.
 *  
 * @author Dražen Đanić
 *
 */
public class SaveAsKeyStoreAction extends AbstractAction {

	private static final long serialVersionUID = -7049295203087653165L;
	
	public SaveAsKeyStoreAction() {
		putValue(NAME, "Save As Keystore");
		putValue(SHORT_DESCRIPTION, "Save key store to new file");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
