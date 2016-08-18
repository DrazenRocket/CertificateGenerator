package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.security.KeyStoreException;
import java.security.cert.Certificate;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.glass.events.KeyEvent;

import certgen.model.table.CertificateTableModel;
import certgen.util.ImageUtil;
import certgen.util.security.CertificateUtil;
import certgen.view.frame.MainFrame;

/**
 * Extended AbstractAction class which performs necessary things for exporting certificate in new file.
 * 
 * @author Dražen Đanić
 */
public class ExportCertificateAction extends AbstractAction {

	private static final long serialVersionUID = -2060060976015860705L;
	
	public ExportCertificateAction() {
		putValue(NAME, "Export Certificate");
		putValue(SHORT_DESCRIPTION, "Export selected certificate in file");
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/export_certificate-icon.png"), 20, 20));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame mf = MainFrame.getInstance();
		
		if (mf.getCurrentKS() != null) {
			int selectedRowIndex  = mf.getTblCertificate().getSelectedRow();
			
			if (selectedRowIndex != -1) {
				String selectedAlias = ((CertificateTableModel) mf.getTblCertificate().getModel()).getAliasAt(selectedRowIndex);
				
				if (selectedAlias != null) {
					try {
						Certificate selectedCertificate = mf.getCurrentKS().getCertificate(selectedAlias);
						
						Object[] fileFormats = {"PEM", "DER"};
						String fileFormat = (String) JOptionPane.showInputDialog(null, "In which format of file you want to export certificate?", "File format", JOptionPane.QUESTION_MESSAGE, null, fileFormats, fileFormats[0]);
						
						if (fileFormat != null) {
							JFileChooser fchExport = new JFileChooser();
							fchExport.setDialogTitle("Export Certificate");
							fchExport.setAcceptAllFileFilterUsed(false);
							fchExport.addChoosableFileFilter(new FileNameExtensionFilter("CER (*.cer)", "cer"));
							fchExport.addChoosableFileFilter(new FileNameExtensionFilter("CRT (*.crt)", "crt"));
							
							if (fileFormat.equals(fileFormats[0])) { //PEM
								fchExport.addChoosableFileFilter(new FileNameExtensionFilter("PEM (*.pem)", "pem"));
								int retVal = fchExport.showSaveDialog(null);
								
								if (retVal == JFileChooser.APPROVE_OPTION) {
									String selectedFilePath = fchExport.getSelectedFile().getAbsolutePath();
									String selectedExtension = ((FileNameExtensionFilter) fchExport.getFileFilter()).getExtensions()[0];
									
									if (!selectedFilePath.endsWith("." + selectedExtension)) {
										selectedFilePath += ".cer";
									}
									
									boolean success = CertificateUtil.saveToPEMFile(selectedCertificate, selectedFilePath);
									
									if (success) {
										JOptionPane.showMessageDialog(null, "Certificate is exported!", "Info", JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(null, "An error has occurred!", "Error", JOptionPane.ERROR_MESSAGE);
									}
								}
							} else if (fileFormat.equals(fileFormats[1])) { //DER
								fchExport.addChoosableFileFilter(new FileNameExtensionFilter("DER (*.der)", "der"));
								int retVal = fchExport.showSaveDialog(null);
								
								if (retVal == JFileChooser.APPROVE_OPTION) {
									String selectedFilePath = fchExport.getSelectedFile().getAbsolutePath();
									String selectedExtension = ((FileNameExtensionFilter) fchExport.getFileFilter()).getExtensions()[0];
									
									if (!selectedFilePath.endsWith("." + selectedExtension)) {
										selectedFilePath += ".cer";
									}
									
									boolean success = CertificateUtil.saveToDERFile(selectedCertificate, selectedFilePath);
									
									if (success) {
										JOptionPane.showMessageDialog(null, "Certificate is exported!", "Info", JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(null, "An error has occurred!", "Error", JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						}
					} catch (KeyStoreException e1) {
						e1.printStackTrace();
						
						JOptionPane.showMessageDialog(null, "Keystore is not initialized (loaded)!", "Error", JOptionPane.ERROR_MESSAGE);
					}	
				} else {
					JOptionPane.showMessageDialog(null, "Alias of certificate is not defined!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Select certificate from table first. If table has not any certificate, create it first.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "There is not current key store. Create it or open first!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
