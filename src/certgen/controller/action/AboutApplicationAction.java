package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import certgen.util.ImageUtil;
import certgen.view.dialog.AboutApplicationDialog;

public class AboutApplicationAction extends AbstractAction {

	private static final long serialVersionUID = 5241155243607597936L;
	
	public AboutApplicationAction() {
		putValue(NAME, "About Application");
		putValue(SHORT_DESCRIPTION, "Short details about application");
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/about-icon.png"), 20, 20));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AboutApplicationDialog aad = new AboutApplicationDialog();
		aad.setVisible(true);
	}
	
}
