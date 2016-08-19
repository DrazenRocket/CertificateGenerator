package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import certgen.model.table.CertificateTableModel;
import certgen.util.ImageUtil;
import certgen.view.dialog.NewCertificateDialog;
import certgen.view.frame.MainFrame;

/**
 * Extended AbstractAction class which performs necessary things for creating new certificate in the current key store.
 * Represents action for creating new certificate.
 * 
 * @author Dražen Đanić
 */
public class NewCertificateAction extends AbstractAction {

	private static final long serialVersionUID = -3713301220809817286L;
	
	public NewCertificateAction() {
		putValue(NAME, "New Certificate");
		putValue(SHORT_DESCRIPTION, "Create new certificate in the current keystore");
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/new_certificate-icon.png"), 20, 20));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame mf = MainFrame.getInstance();
		
		if (mf.getCurrentKS() != null) {
			NewCertificateDialog ncd = new NewCertificateDialog(mf.getCurrentKS());
			ncd.setVisible(true);
			
			if (ncd.isCreated()) {
				((CertificateTableModel) mf.getTblCertificate().getModel()).setRowCount(0);
				((CertificateTableModel) mf.getTblCertificate().getModel()).addRows(mf.getCurrentKS());
				mf.setChangedKS(true);
				
				mf.refreshStatusBar();
			}
			
			ncd.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Thre is not current keystore. Create or open it first.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
