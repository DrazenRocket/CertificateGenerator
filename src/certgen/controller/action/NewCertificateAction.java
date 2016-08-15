package certgen.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Extended AbstractAction class which performs necessary things for creating new certificate in current key store.
 * 
 * @author Dražen Đanić
 */
public class NewCertificateAction extends AbstractAction {

	private static final long serialVersionUID = -3713301220809817286L;
	
	public NewCertificateAction() {
		putValue(NAME, "New Certificate");
		putValue(SHORT_DESCRIPTION, "Create new certificate in current keystore");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
