package certgen.view.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Represents main frame of application.
 * It is realized using singleton pattern. 
 * 
 * @author Dražen Đanić
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -3928909859611520131L;
	private static MainFrame instance;
	
	private MainFrame() {
		
	}
	
	private void initialize() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		double frameWidth = (screenWidth * 3) / 5;
		double frameHeight = (screenHeight * 3) / 5;
		
		setSize((int) frameWidth, (int) frameHeight);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("/certgen/resource/img/keystore-icon.png")).getImage());
		setTitle("Certificate Generator");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Change with confirm dialog if something is not saved!
				// For now:
				System.exit(0);
			}
		});
	}
	
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
			instance.initialize();
		}
		
		return instance;
	}

}
