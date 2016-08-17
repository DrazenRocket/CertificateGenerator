package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import certgen.util.ImageUtil;
import certgen.view.frame.MainFrame;

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
		MainFrame mf = MainFrame.getInstance();
		boolean openKS = false;
		
		if (mf.isChangedKS()) {
			int answer = JOptionPane.showConfirmDialog(null, "Do you want to save chages of current key store first?", "Save changes?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if (answer == JOptionPane.YES_OPTION) {
				mf.getActionManager().getSaveKeyStoreAction().actionPerformed(null);
				// TODO enable opening after saving
			} else if (answer == JOptionPane.NO_OPTION) {
				openKS = true;
			}
		} else {
			openKS = true;
		}
		
		if (openKS) {
			// TODO Open key store
		}
	}

}
