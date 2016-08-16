package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import certgen.util.ImageUtil;

public class OpenKeyStoreAction extends AbstractAction {

	private static final long serialVersionUID = -3632611403117820934L;
	
	public OpenKeyStoreAction() {
		putValue(NAME, "Open Keystore");
		putValue(SHORT_DESCRIPTION, "Open existing keystore");
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/open-icon.png"), 20, 20));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
