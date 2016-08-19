package certgen.view.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import certgen.util.ImageUtil;
import certgen.util.security.KeyStoreUtil;
import net.miginfocom.swing.MigLayout;

/**
 * Represents modal dialog for creating new certificate.
 * 
 * @author Dražen Đanić
 */
public class NewCertificateDialog extends JDialog {

	private static final long serialVersionUID = -1858201336889233752L;
	private KeyStore keyStore;
	private JComboBox<String> cmbIssuer;
	private JCheckBox chkSelfSigned;
	private JTextField txfName;
	private JTextField txfSurname;
	private JTextField txfCommonName;
	private JTextField txfOrganisationUnit;
	private JTextField txfOrganisation;
	private JTextField txfCountry;
	private JTextField txfEmail;
	private JTextField txfUserID;
	private JTextField txfValidFor;
	private boolean created;
	
	public NewCertificateDialog(KeyStore keyStore) {
		this.keyStore = keyStore;
		cmbIssuer = new JComboBox<String>(KeyStoreUtil.getKeyEntryAliases(keyStore));
		chkSelfSigned = new JCheckBox("Self signed: ");
		chkSelfSigned.setHorizontalTextPosition(SwingConstants.LEFT);
		txfName = new JTextField(20);
		txfSurname = new JTextField(20);
		txfCommonName = new JTextField(20);
		txfOrganisationUnit = new JTextField(20);
		txfOrganisation = new JTextField(20);
		txfCountry = new JTextField(2);
		txfEmail = new JTextField(20);
		txfUserID = new JTextField(20);
		txfValidFor = new JTextField(20);
		
		setIconImage(ImageUtil.loadImage(getClass().getResource("/certgen/resource/img/new_certificate-icon.png")));
		setTitle("New Certificate");
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		
		addComponents();
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void addComponents() {
		setLayout(new MigLayout("fill"));
		
		JPanel pnlHeader = new JPanel(new MigLayout("fill"));
		pnlHeader.add(new JLabel("Fill in next filds to create new certificate."));
		add(pnlHeader, "dock north");
		
		JPanel pnlCenter = new JPanel(new MigLayout("fill"));
		pnlCenter.add(new JLabel("Issuer: "));
		pnlCenter.add(cmbIssuer, "wrap");
		pnlCenter.add(chkSelfSigned, "wrap");
		pnlCenter.add(new JLabel("Name: "));
		pnlCenter.add(txfName, "wrap");
		pnlCenter.add(new JLabel("Surname: "));
		pnlCenter.add(txfSurname, "wrap");
		pnlCenter.add(new JLabel("Common Name [CN]: "));
		pnlCenter.add(txfCommonName, "wrap");
		pnlCenter.add(new JLabel("Organisation Unit [OU]: "));
		pnlCenter.add(txfOrganisationUnit, "wrap");
		pnlCenter.add(new JLabel("Organisation [O]: "));
		pnlCenter.add(txfOrganisation, "wrap");
		pnlCenter.add(new JLabel("Country [C]: "));
		pnlCenter.add(txfCountry, "wrap");
		pnlCenter.add(new JLabel("Email [E]: "));
		pnlCenter.add(txfEmail, "wrap");
		pnlCenter.add(new JLabel("User ID [UID]: "));
		pnlCenter.add(txfUserID, "wrap");
		pnlCenter.add(new JLabel("Valid for (months): "));
		pnlCenter.add(txfValidFor, "wrap");
		add(pnlCenter, "dock center");
		
		// JScrollPane scrCenter = new JScrollPane(pnlCenter);
		// add(scrCenter, "dock center");
		
		JPanel pnlFooter = new JPanel(new MigLayout("fill"));
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");
		pnlFooter.add(btnOk, "split 2, gapleft push");
		pnlFooter.add(btnCancel);
		add(pnlFooter, "dock south");
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				created = false;
				setVisible(false);
			}
			
		});
	}
	
	public boolean isCreated() {
		return created;
	}

}
