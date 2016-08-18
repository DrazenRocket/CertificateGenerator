package certgen.view.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import certgen.util.ImageUtil;
import net.miginfocom.swing.MigLayout;

/**
 * Represents dialog for creating new password.
 * 
 * @author Dražen Đanić
 */
public class NewPasswordDialog extends JDialog {

	private static final long serialVersionUID = -8219984742972907240L;
	private char[] newPassword;
	private JPasswordField pswNewPassword;
	private JPasswordField pswConfirmNewPassword;
	
	/**
	 * Creates new modal dialog which requires of user to type new password.
	 */
	public NewPasswordDialog() {
		pswNewPassword = new JPasswordField(15);
		pswConfirmNewPassword = new JPasswordField(15);
		
		setIconImage(ImageUtil.loadImage(getClass().getResource("/certgen/resource/img/password-icon.png")));
		setTitle("New Password");
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
		pnlHeader.add(new JLabel("Enter new password to lock"));
		add(pnlHeader, "dock north");
		
		JPanel pnlCenter = new JPanel(new MigLayout("fill"));
		pnlCenter.add(new JLabel("New Password: "));
		pnlCenter.add(pswNewPassword, "wrap");
		pnlCenter.add(new JLabel("Confirm New Password: "));
		pnlCenter.add(pswConfirmNewPassword, "wrap");
		add(pnlCenter, "dock center");
		
		JPanel pnlFooter = new JPanel(new MigLayout("fill"));
		JButton btnCreate = new JButton("Create");
		JButton btnCancel = new JButton("Cancel");
		pnlFooter.add(btnCreate, "split 2, gapleft push");
		pnlFooter.add(btnCancel);
		add(pnlFooter, "dock south");
		
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				char[] password = pswNewPassword.getPassword();
				char[] confirmPassword = pswConfirmNewPassword.getPassword();
				
				if (password.length==0 || confirmPassword.length==0) {
					JOptionPane.showMessageDialog(null, "You must type your password in both fields!", "Error", JOptionPane.ERROR_MESSAGE);
					pswNewPassword.setText("");
					pswConfirmNewPassword.setText("");
				}  else if (!Arrays.equals(password, confirmPassword)) {
					JOptionPane.showMessageDialog(null, "You must type same passwords in both fields!", "Error", JOptionPane.ERROR_MESSAGE);
					pswNewPassword.setText("");
					pswConfirmNewPassword.setText("");
				} else {
					newPassword = password;
					setVisible(false);
				}
			}
			
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newPassword = null;
				setVisible(false);
			}
			
		});
	}
	
	/**
	 * Returns new password typed by user. 
	 * If user cancel action, returns <code>null</code>.
	 * 
	 * @return new password
	 */
	public char[] getNewPassword() {
		return newPassword;
	}
	
}
