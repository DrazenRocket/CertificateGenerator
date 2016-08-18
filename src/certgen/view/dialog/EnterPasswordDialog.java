package certgen.view.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import certgen.util.ImageUtil;
import net.miginfocom.swing.MigLayout;

/**
 * Represents dialog for getting password which can be used to unlock something.
 * 
 * @author Dražen Đanić
 */
public class EnterPasswordDialog extends JDialog {

	private static final long serialVersionUID = -5961273063565011554L;
	private char[] enteredPassword;
	private JPasswordField pswEnteredPassword;
	
	public EnterPasswordDialog() {
		pswEnteredPassword = new JPasswordField(15);
		
		setIconImage(ImageUtil.loadImage(getClass().getResource("/certgen/resource/img/password-icon.png")));
		setTitle("Enter Password");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		
		addComponents();
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void addComponents() {
		setLayout(new MigLayout("fill"));
		
		JPanel pnlHeader = new JPanel(new MigLayout("fill"));
		pnlHeader.add(new JLabel("Enter password to unlock"));
		add(pnlHeader, "dock north");
		
		JPanel pnlCenter = new JPanel(new MigLayout("fill"));
		pnlCenter.add(new JLabel("Password: "));
		pnlCenter.add(pswEnteredPassword, "wrap");
		add(pnlCenter, "dock center");
		
		JPanel pnlFooter = new JPanel(new MigLayout("fill"));
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");
		pnlFooter.add(btnOk, "split 2, gapleft push");
		pnlFooter.add(btnCancel);
		add(pnlFooter, "dock south");
		
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				char[] password = pswEnteredPassword.getPassword();
				
				if (password.length > 0) {
					enteredPassword = password;				
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "You must type password to unlock!", "Error", JOptionPane.ERROR_MESSAGE);					
					pswEnteredPassword.setText("");
				}
			}
			
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arb0) {
				enteredPassword = null;
				setVisible(false);
			}
		});
	}
	
	/**
	 * Returns password which is entered by user.
	 * If user cancel action, returns <code>null</code>.
	 * 
	 * @return entered password 
	 */
	public char[] getEnteredPassword() {
		return enteredPassword;
	}
	
}
