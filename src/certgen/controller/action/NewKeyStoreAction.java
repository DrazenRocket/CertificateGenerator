package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.security.KeyStore;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import certgen.model.table.CertificateTableModel;
import certgen.util.ImageUtil;
import certgen.util.security.KeyStoreUtil;
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
		boolean createNewKS = false;
		
		if (mf.isChangedKS()) {
			int answer = JOptionPane.showConfirmDialog(null, "Do you want to save chages of the current keystore first?", "Save changes?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if (answer == JOptionPane.YES_OPTION) {
				mf.getActionManager().getSaveKeyStoreAction().actionPerformed(null);
				// TODO enable creating after saving (it will require to add new methods in classes for save and save as) which will return indicator
			} else if (answer == JOptionPane.NO_OPTION) {
				createNewKS = true;
			}
		} else {
			createNewKS = true;
		}
		
		if (createNewKS) {
			KeyStore newKS = KeyStoreUtil.loadKeyStore(null, "password".toCharArray());
			
			if (newKS != null) {
				mf.setCurrentKS(newKS);
				mf.setCurrentKSFilePath(null);
				mf.setChangedKS(true);
				((CertificateTableModel) mf.getTblCertificate().getModel()).setRowCount(0);
			} else {
				JOptionPane.showMessageDialog(null, "An error has occurred while creating keystore!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
