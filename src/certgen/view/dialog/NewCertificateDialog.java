package certgen.view.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;

import certgen.model.security.IssuerData;
import certgen.model.security.SubjectData;
import certgen.util.ImageUtil;
import certgen.util.security.CertificateUtil;
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
		chkSelfSigned = new JCheckBox();
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
		pnlHeader.add(new JLabel("Fill in next fields to create new certificate."));
		add(pnlHeader, "dock north");
		
		JPanel pnlCenter = new JPanel(new MigLayout("fill"));
		pnlCenter.add(new JLabel("Issuer: "));
		pnlCenter.add(cmbIssuer, "wrap");
		pnlCenter.add(new JLabel("Self Signed: "));
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
		pnlCenter.add(new JLabel("Valid for (days): "));
		pnlCenter.add(txfValidFor, "wrap");
		add(pnlCenter, "dock center");
		
		JPanel pnlFooter = new JPanel(new MigLayout("fill"));
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");
		pnlFooter.add(btnOk, "split 2, gapleft push");
		pnlFooter.add(btnCancel);
		add(pnlFooter, "dock south");
		
		if (cmbIssuer.getItemCount() == 0) {
			cmbIssuer.setEnabled(false);
			chkSelfSigned.setSelected(true);
			chkSelfSigned.setEnabled(false);
		}
		
		chkSelfSigned.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cmbIssuer.setEnabled(!chkSelfSigned.isSelected());
			}
			
		});
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String issuer = (String) cmbIssuer.getSelectedItem(); // null if nothing is selected
				boolean selfSigned = chkSelfSigned.isSelected();
				String name = txfName.getText().trim();
				String surname = txfSurname.getText().trim();
				String commonName = txfCommonName.getText().trim();
				String organisationUnit = txfOrganisationUnit.getText().trim();
				String organisation = txfOrganisation.getText().trim();
				String country = txfCountry.getText().trim();
				String email = txfEmail.getText().trim();
				String userID = txfUserID.getText().trim();
				String validFor = txfValidFor.getText().trim();
				
				Pattern emailPattern = Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[a-zA-Z0-9](?:[\\w-]*[\\w])?");
				
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "You must type name of subject!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (surname.equals("")) {
					JOptionPane.showMessageDialog(null, "You must type surname of subject", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (commonName.equals("")) {
					JOptionPane.showMessageDialog(null, "You must type common name of subject!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (organisationUnit.equals("")) {
					JOptionPane.showMessageDialog(null, "You must type organisation unit of subject!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (organisation.equals("")) {
					JOptionPane.showMessageDialog(null, "You must type organisation of subject", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (country.equals("") || country.length()!=2) {
					JOptionPane.showMessageDialog(null, "You must type code of country which has two char!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (email.equals("") || !emailPattern.matcher(email).matches()) {
					JOptionPane.showMessageDialog(null, "You must type email of subject and it must be in valid format!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (userID.equals("")) {
					JOptionPane.showMessageDialog(null, "You must type user id of subject!", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (validFor.equals("")) {
					JOptionPane.showMessageDialog(null, "You must type number of days to tell valid period of certificate!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					int validForInt = 1;
					
					try {
						validForInt = Integer.parseInt(validFor);
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Type valid format of numer for field 'Valid For'!", "Error", JOptionPane.ERROR_MESSAGE);
						
						return;
					}
					
					if (validForInt < 1) {
						JOptionPane.showMessageDialog(null, "Number of days (Valid For) must be bigger than zero!", "Error", JOptionPane.ERROR_MESSAGE);
						
						return;
					}
					
					KeyPair keyPair = CertificateUtil.generateKeyPair();
					
					if (keyPair == null) {
						JOptionPane.showMessageDialog(null, "Certificate is not created successfully. Some error has occured! Keys has not created propertly!", "Error", JOptionPane.ERROR_MESSAGE);
						
						return;
					}
					
					Date validOfDate = new Date();
					Calendar c = Calendar.getInstance();
					c.setTime(validOfDate);
					c.add(Calendar.DATE, validForInt);
					Date validToDate = c.getTime();
					
					if (!selfSigned && issuer!=null) {
						try {
							Date issuerValidToDate = ((X509Certificate) keyStore.getCertificate(issuer)).getNotAfter();
							System.out.println(issuerValidToDate);
							if (validOfDate.compareTo(issuerValidToDate) > 0) {
								JOptionPane.showMessageDialog(null, "You can't use selcted issuer because its certificate expired.", "Error", JOptionPane.ERROR_MESSAGE);
								
								return;
							}
							
							if (validToDate.compareTo(issuerValidToDate) > 0) {
								JOptionPane.showMessageDialog(null, "Filed 'Valid For' is too big because issuer's certificate doesn't cover it.", "Error", JOptionPane.ERROR_MESSAGE);
								
								return;
							}
						} catch (KeyStoreException e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "Keystore is not loaded!", "Error", JOptionPane.ERROR_MESSAGE);
							
							return;
						}
					}
					
					X500NameBuilder builderSubject = new X500NameBuilder(BCStyle.INSTANCE);
					builderSubject.addRDN(BCStyle.NAME, name);
					builderSubject.addRDN(BCStyle.SURNAME, surname);
					builderSubject.addRDN(BCStyle.CN, commonName);
					builderSubject.addRDN(BCStyle.OU, organisationUnit);
					builderSubject.addRDN(BCStyle.O, organisation);
					builderSubject.addRDN(BCStyle.C, country);
					builderSubject.addRDN(BCStyle.E, email);
					builderSubject.addRDN(BCStyle.UID, userID);
					
					String serialNumber = "123"; // TODO generate serial number
					
					IssuerData issuerData = null;
					X500Name issuerX500Name = null;
					PrivateKey issuerPrivateKey = null;
					
					SubjectData subjectData = null;
					X500Name subjectX500Name = builderSubject.build();
					PrivateKey subjectPrivateKey = keyPair.getPrivate();
					PublicKey subjectPublicKey = keyPair.getPublic();		
					
					if (selfSigned) {
						issuerX500Name = builderSubject.build();
						issuerPrivateKey = keyPair.getPrivate();
					} else {
						// User type password of issuer to get the private key of issuer...
						boolean tryAgain = true;
						
						while (tryAgain) {
							EnterPasswordDialog epd = new EnterPasswordDialog();
							epd.setTitle("Enter Password Of Issuer");
							epd.setVisible(true);
							
							char[] enteredPassword = epd.getEnteredPassword();
							epd.dispose();
							
							if (enteredPassword != null) {
								// Button OK
								try {
									issuerPrivateKey = (PrivateKey) keyStore.getKey(issuer, enteredPassword);
									issuerX500Name = new JcaX509CertificateHolder((X509Certificate) keyStore.getCertificate(issuer)).getSubject();
									tryAgain = false;
								} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException | CertificateEncodingException e1) {
									e1.printStackTrace();
									JOptionPane.showMessageDialog(null, "You have typed wrong password of issuer. You can try again.", "Error", JOptionPane.ERROR_MESSAGE);
									tryAgain = true;
								}
							} else {
								// Button Cancel
								return;
							}
						}
					}
					
					issuerData = new IssuerData(issuerX500Name, issuerPrivateKey);
					subjectData = new SubjectData(subjectX500Name, subjectPublicKey, serialNumber, validOfDate, validToDate);
					
					X509Certificate newCertificate = CertificateUtil.generateCertificate(issuerData, subjectData);
					
					if (newCertificate != null) {
						// Successfully created
						// Traziti od korisnika da unese alias koji ne postoji
						// Traziti od korisnika da unese novi password
						// Ubaciti u keystore
						// postaviti success na true i zatvoriti
						
						boolean tryAgain = true;
						String certificateAlias = null;
						
						while (tryAgain) {
							certificateAlias = JOptionPane.showInputDialog(null, "Enter alias for new certificate which will be use to add it in the cuccent keystore", "Alias For Certificate", JOptionPane.QUESTION_MESSAGE);
						
							if (certificateAlias != null) {
								// Button OK
								certificateAlias = certificateAlias.trim();
								
								if (!certificateAlias.equals("")) {
									try {
										tryAgain = keyStore.containsAlias(certificateAlias);
										
										if (tryAgain) {
											JOptionPane.showMessageDialog(null, "Alias is already used. You can try again!", "Error", JOptionPane.ERROR_MESSAGE);
										}
									} catch (KeyStoreException e1) {
										e1.printStackTrace();
										JOptionPane.showMessageDialog(null, "Keystore is not initialized (loaded).", "Error", JOptionPane.ERROR_MESSAGE);
										return;
									}
								} else {
									JOptionPane.showMessageDialog(null, "You must type alias for certificate to continue. You can try again", "Error", JOptionPane.ERROR_MESSAGE);
									tryAgain = true;
								}
							} else {
								// Button Cancel
								return;
							}
						}
						
						NewPasswordDialog npd = new NewPasswordDialog();
						npd.setVisible(true);
						
						char[] newPassword = npd.getNewPassword();
						npd.dispose();
						
						if (newPassword != null) {
							KeyStoreUtil.setKeyEntry(keyStore, certificateAlias, subjectPrivateKey, newPassword, newCertificate);
							created = true;
							setVisible(false);
							return; // This can be deleted
						} else {
							return;	// This can be deleted
						}
					} else {
						JOptionPane.showMessageDialog(null, "Certificate is not generated successfully!", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
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
	
	/**
	 * Returns <code>true</code> if the certificate is created.
	 * Otherwise <code>false</code>
	 * 
	 * @return indicator
	 */
	public boolean isCreated() {
		return created;
	}

}
