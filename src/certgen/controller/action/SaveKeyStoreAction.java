package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import certgen.util.ImageUtil;

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
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/save-icon.png"), 20, 20));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
