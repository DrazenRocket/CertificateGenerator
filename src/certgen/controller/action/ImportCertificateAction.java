package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import certgen.model.table.CertificateTableModel;
import certgen.util.ImageUtil;
import certgen.util.security.CertificateUtil;
import certgen.util.security.KeyStoreUtil;
import certgen.view.frame.MainFrame;

/**
 * Extends AbstractAction and represents action for 'Import trusted certificate'.
 * 
 * @author Dražen Đanić
 */
public class ImportCertificateAction extends AbstractAction {

	private static final long serialVersionUID = 695516837106484625L;
	
	public ImportCertificateAction() {
		putValue(NAME, "Import Trusted Certificate");
		putValue(SHORT_DESCRIPTION, "Import trusted certificate from file");
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/import_certificate-icon.png"), 20, 20));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame mf = MainFrame.getInstance();
		
		if (mf.getCurrentKS() != null) {
			KeyStore currentKS = mf.getCurrentKS();
			
			JFileChooser fchCertificate = new JFileChooser();
			fchCertificate.setDialogTitle("Import Trusted Certificate");
			fchCertificate.setAcceptAllFileFilterUsed(false);
			fchCertificate.setFileFilter(new FileNameExtensionFilter("Certificate (*.cer, *.crt, *.der, *.pem)", "cer", "crt", "der", "pem"));
			fchCertificate.addChoosableFileFilter(new FileNameExtensionFilter("Certificate (*.cer, *.crt)", "cer","crt"));
			fchCertificate.addChoosableFileFilter(new FileNameExtensionFilter("Certificate (*.der)", "der"));
			fchCertificate.addChoosableFileFilter(new FileNameExtensionFilter("Certificate (*.pem)", "pem"));
			int retVal = fchCertificate.showOpenDialog(null);
			
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File importCertificateFile = fchCertificate.getSelectedFile();
				String importCertificatePath = importCertificateFile.getAbsolutePath();
				
				boolean tryTypeAliasAgain = false;
				
				do {
					tryTypeAliasAgain = false;
					
					String certificateAlias = JOptionPane.showInputDialog(null, "Enter alias for certificate which will be imported.", "Alias For Certificate", JOptionPane.QUESTION_MESSAGE);
					
					if (certificateAlias != null) {
						// Clicked button 'OK'
						certificateAlias = certificateAlias.trim();
						
						if (!certificateAlias.equals("")) {
							try {
								boolean exists = currentKS.containsAlias(certificateAlias);
								
								if (!exists) {
									Certificate certificate = CertificateUtil.readFromFile(importCertificatePath);
									
									if (certificate != null) {
										boolean success = KeyStoreUtil.setCertificateEntry(currentKS, certificateAlias,  certificate);
										
										if (success) {
											mf.setChangedKS(true);
											((CertificateTableModel) mf.getTblCertificate().getModel()).setRowCount(0);
											((CertificateTableModel) mf.getTblCertificate().getModel()).addRows(currentKS);
											
											mf.refreshStatusBar();
										} else {
											JOptionPane.showMessageDialog(null, "Certificate is not imported successfully!", "Error", JOptionPane.ERROR_MESSAGE);
										}
									} else {
										JOptionPane.showMessageDialog(null, "Certificate is not imported successfully!", "Error", JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null, "Alias is already used. You can try again.", "Error", JOptionPane.ERROR_MESSAGE);
									tryTypeAliasAgain = true;
								}
							} catch (KeyStoreException e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "Keystore isn't loaded.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "To import certificate, you must type its alias which will be used in the current keystory!", "Error", JOptionPane.ERROR_MESSAGE);
							tryTypeAliasAgain = true;
						}
					}
				} while (tryTypeAliasAgain);
			}
		} else {
			JOptionPane.showMessageDialog(null, "There is not active keystore. Crate or open it first.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
