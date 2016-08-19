package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import certgen.util.ImageUtil;
import certgen.util.security.KeyStoreUtil;
import certgen.view.dialog.NewPasswordDialog;
import certgen.view.frame.MainFrame;

/**
 * Extended AbstractAction class which performs necessary things for new saving key store to file.
 *  
 * @author Dražen Đanić
 *
 */
public class SaveAsKeyStoreAction extends AbstractAction {

	private static final long serialVersionUID = -7049295203087653165L;
	
	public SaveAsKeyStoreAction() {
		putValue(NAME, "Save As Keystore");
		putValue(SHORT_DESCRIPTION, "Save key store to new file");
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/save_as-icon.png"), 20, 20));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame mf = MainFrame.getInstance();
		
		if (mf.getCurrentKS() != null) {
			JFileChooser fchKeyStore = new JFileChooser();
			fchKeyStore.setDialogTitle("Save Keystore");
			fchKeyStore.setAcceptAllFileFilterUsed(false);
			fchKeyStore.setFileFilter(new FileNameExtensionFilter("Java KeyStore file (*.jks)", "jks"));
			int retVal = fchKeyStore.showSaveDialog(null);
			
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File selectedKSFile = fchKeyStore.getSelectedFile();
				String selectedKSPath = selectedKSFile.getAbsolutePath();
				
				if (!selectedKSPath.endsWith(".jks")) {
					selectedKSPath += ".jks";
				}
				
				NewPasswordDialog npd = new NewPasswordDialog();
				npd.setVisible(true);
				
				char[] newPassword = npd.getNewPassword();
				npd.dispose();
				
				if (newPassword != null) {
					boolean successSave = KeyStoreUtil.saveKeyStore(mf.getCurrentKS(), selectedKSPath, newPassword);
					
					if (successSave) {
						// Successfully saved 
						mf.setCurrentKSFilePath(selectedKSPath);
						mf.setChangedKS(false);
						mf.refreshStatusBar();
					} else {
						JOptionPane.showMessageDialog(null, "An error has occurred while saving keystore!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "You must create or open a keystore first!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
