package certgen.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class OpenKeyStoreAction extends AbstractAction {

	private static final long serialVersionUID = -3632611403117820934L;
	
	public OpenKeyStoreAction() {
		putValue(NAME, "Open Keystore");
		putValue(SHORT_DESCRIPTION, "Open existing keystore");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
