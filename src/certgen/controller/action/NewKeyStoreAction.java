package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import certgen.util.ImageUtil;

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
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/new_keystore-icon.png"), 20, 20));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
