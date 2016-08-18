package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.security.KeyStoreException;
import java.security.cert.Certificate;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import certgen.model.table.CertificateTableModel;
import certgen.util.ImageUtil;
import certgen.view.dialog.CertificateDetailsDialog;
import certgen.view.frame.MainFrame;

/**
 * Extended AbstractAction class which perform necessary things to show details of selected certificate.
 * 
 * @author Dražen Đanić
 */
public class ShowCertificateAction extends AbstractAction {

	private static final long serialVersionUID = 8733006979096328842L;
	
	public ShowCertificateAction() {
		putValue(NAME, "Show Certificate");
		putValue(SHORT_DESCRIPTION, "Show details of selected certificate");
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/certificate-icon.png"), 20, 20));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame mf = MainFrame.getInstance();
		
		int selectedRowIndex = mf.getTblCertificate().getSelectedRow();
		
		if (selectedRowIndex != -1) {
			String alias = ((CertificateTableModel) mf.getTblCertificate().getModel()).getAliasAt(selectedRowIndex);
			
			if (alias != null) {
				try {
					Certificate certificate = mf.getCurrentKS().getCertificate(alias);
					
					CertificateDetailsDialog cdd = new CertificateDetailsDialog(certificate);
					cdd.setVisible(true);
					cdd.dispose();
				} catch (KeyStoreException e1) {
					e1.printStackTrace();
					
					JOptionPane.showMessageDialog(null, "Keystore is not correctly loaded.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "You must select row in table first!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
