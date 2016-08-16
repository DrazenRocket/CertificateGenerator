package certgen.app;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import certgen.view.frame.MainFrame;

/**
 * Class with method which represents entry 
 * point for application of this project.
 * 
 * @author Dražen Đanić
 */
public class Application {

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MainFrame mf = MainFrame.getInstance();
		mf.setVisible(true);
	}
	
}
