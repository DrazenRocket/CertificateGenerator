package certgen.view.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import certgen.util.ImageUtil;
import net.miginfocom.swing.MigLayout;

/**
 * Represents modal dialog which offer basic information about application.
 * 
 * @author Dražen Đanić
 */
public class AboutApplicationDialog extends JDialog {

	private static final long serialVersionUID = -2211114778743725807L;
	private String respositoryLink;

	public AboutApplicationDialog() {
		respositoryLink = "https://github.com/DrazenRocket/CertificateGenerator";
		
		setIconImage(ImageUtil.loadImage(getClass().getResource("/certgen/resource/img/about-icon.png")));
		setTitle("About Certificate Generator");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		
		addComponents();
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void addComponents() {
		setLayout(new MigLayout("fill"));
		
		JPanel pnlHeader = new JPanel(new MigLayout("fill"));
		JLabel lblHeader = new JLabel("Certificate Generator");
		lblHeader.setFont(new Font("Comic Sans MS", Font.ITALIC, 25));
		lblHeader.setForeground(new Color(0, 0, 255));
		pnlHeader.add(lblHeader, "wrap");
		add(pnlHeader, "dock north");
		
		JPanel pnlCenter = new JPanel(new MigLayout("fill"));
		pnlCenter.add(new JLabel("Application :"));
		pnlCenter.add(new JLabel("Certificate Generator"), "wrap");
		pnlCenter.add(new JLabel("Author: "));
		pnlCenter.add(new JLabel("Dražen Đanić"), "wrap");
		pnlCenter.add(new JLabel("GitHub Respository: "));
		pnlCenter.add(new JLabel(respositoryLink), "split 2");
		JButton btnCopyLink = new JButton("Copy Link");
		pnlCenter.add(btnCopyLink, "wrap");
		add(pnlCenter, "dock center");
		
		JPanel pnlFooter = new JPanel(new MigLayout("fill"));
		JButton btnOk = new JButton("OK");
		pnlFooter.add(btnOk, "gapleft push");
		add(pnlFooter, "dock south");
		
		btnCopyLink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(respositoryLink);
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
			}
			
		});
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
	}
	
}
