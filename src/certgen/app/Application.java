package certgen.app;

import certgen.view.frame.MainFrame;

/**
 * Class with method which represents entry 
 * point for application of this project.
 * 
 * @author Dražen Đanić
 */
public class Application {

	public static void main(String[] args) {
		MainFrame mf = MainFrame.getInstance();
		mf.setVisible(true);
	}
	
}
