package certgen.view.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.cert.Certificate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import certgen.util.ImageUtil;
import net.miginfocom.swing.MigLayout;

/**
 * Represents modal dialog which show details of certificate
 * 
 * @author Dražen Đanić
 */
public class CertificateDetailsDialog extends JDialog {

	private static final long serialVersionUID = -5046029588703242838L;
	
	public CertificateDetailsDialog(Certificate certificate) {
		setIconImage(ImageUtil.loadImage(getClass().getResource("/certgen/resource/img/certificate-icon.png")));
		setTitle("Details Of Certificate");
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		//setResizable(false);
		setModal(true);
		
		addComponents(certificate);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void addComponents(Certificate certificate) {
		setLayout(new MigLayout("fill"));
		
		JPanel pnlHeader = new JPanel(new MigLayout("fill"));
		pnlHeader.add(new JLabel("Details of certificate: "), "wrap");
		add(pnlHeader, "dock north");
		
		JTextArea txaCertificateDetails = new JTextArea(certificate.toString(), 25, 40);
		txaCertificateDetails.setEditable(false);
		JScrollPane scrCenter = new JScrollPane(txaCertificateDetails);
		add(scrCenter, "dock center");
		
		JPanel pnlFooter = new JPanel(new MigLayout("fill"));
		JButton btnOk = new JButton("OK");
		pnlFooter.add(btnOk, "gapleft push");
		add(pnlFooter, "dock south");
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
	}

}
