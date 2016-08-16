package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import certgen.util.ImageUtil;

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
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/new_certificate-icon.png"), 20, 20));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
