package certgen.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Extended AbstractAction class which performs necessary things for creating new key store. 
 * 
 * @author Dražen Đanić
 */
public class NewKeyStoreAction extends AbstractAction {

	private static final long serialVersionUID = -6691436041870314603L;
	
	public NewKeyStoreAction() {
		putValue(NAME, "New Keystore");
		putValue(SHORT_DESCRIPTION, "Create new keystore");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
