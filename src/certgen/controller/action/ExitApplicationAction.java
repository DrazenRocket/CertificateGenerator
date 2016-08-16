package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import certgen.util.ImageUtil;

/**
 * Extended AbstractAction class which performs necessary things for exiting application. 
 * 
 * @author Dražen Đanić
 */
public class ExitApplicationAction extends AbstractAction {

	private static final long serialVersionUID = 8698556893635553804L;
	
	public ExitApplicationAction() {
		putValue(NAME, "Exit");
		putValue(SHORT_DESCRIPTION, "Exit application");
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/exit-icon.png"), 20, 20));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
		// For now!
		System.exit(0);
	}

}
