package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.security.KeyStore;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import certgen.util.ImageUtil;
import certgen.util.security.KeyStoreUtil;
import certgen.view.dialog.NewPasswordDialog;
import certgen.view.frame.MainFrame;

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
		MainFrame mf = MainFrame.getInstance();
		
		if (mf.isChangedKS()) {
			if (mf.getCurrentKSFilePath() != null) {
				NewPasswordDialog npd = new NewPasswordDialog();
				npd.setVisible(true);
				
				char[] newPassword = npd.getNewPassword();
				npd.dispose();
				
				if (newPassword != null) {
					KeyStore keyStore = mf.getCurrentKS();
					String filePath = mf.getCurrentKSFilePath();
					
					boolean success = KeyStoreUtil.saveKeyStore(keyStore, filePath, newPassword);
					
					if (success) {
						// Successfully saved
						mf.setChangedKS(false);
						mf.refreshStatusBar();
					} else {
						JOptionPane.showMessageDialog(null, "An error has occurred while saving keystore!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				mf.getActionManager().getSaveAsKeyStoreAction().actionPerformed(null);
			}
		}
	}

}
