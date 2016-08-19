package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.security.KeyStore;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import certgen.model.table.CertificateTableModel;
import certgen.util.ImageUtil;
import certgen.util.security.KeyStoreUtil;
import certgen.view.dialog.EnterPasswordDialog;
import certgen.view.frame.MainFrame;

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
		MainFrame mf = MainFrame.getInstance();
		boolean continueOpenKS = false;
		
		if (mf.isChangedKS()) {
			int answer = JOptionPane.showConfirmDialog(null, "Do you want to save changes of the current keystore first?", "Save changes?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if (answer == JOptionPane.YES_OPTION) {
				mf.getActionManager().getSaveKeyStoreAction().actionPerformed(null);
				// TODO enable opening after saving (it will require to add new methods in classes for save and save as) which will return indicator
			} else if (answer == JOptionPane.NO_OPTION) {
				continueOpenKS = true;
			}
		} else {
			continueOpenKS = true;
		}
		
		if (continueOpenKS) {
			JFileChooser fch = new JFileChooser();
			fch.setDialogTitle("Open Keystore");
			fch.setAcceptAllFileFilterUsed(false);
			fch.setFileFilter(new FileNameExtensionFilter("Java KeyStore (*.jks)", "jks"));
			int retVal = fch.showOpenDialog(null);
			
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File openKSFile = fch.getSelectedFile();
				String openKSPath = openKSFile.getAbsolutePath();
				KeyStore openKS = null;
				char[] enteredPassword = null;
								
				do {
					EnterPasswordDialog epd = new EnterPasswordDialog();
					epd.setVisible(true);
					
					enteredPassword = epd.getEnteredPassword();
					epd.dispose();
					
					if (enteredPassword != null) {
						openKS = KeyStoreUtil.loadKeyStore(openKSPath, enteredPassword);
						
						if (openKS != null) {
							// Successfully opened 
							mf.setCurrentKS(openKS);
							mf.setCurrentKSFilePath(openKSPath);
							mf.setChangedKS(false);
							((CertificateTableModel) mf.getTblCertificate().getModel()).setRowCount(0);
							((CertificateTableModel) mf.getTblCertificate().getModel()).addRows(openKS);
							mf.refreshStatusBar();
						} else {
							JOptionPane.showMessageDialog(null, "An error has occurred while opening keystore! \nMaybe you have typed wrong password.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} while(openKS==null && enteredPassword!=null);
			}
		}
		
	}

}
