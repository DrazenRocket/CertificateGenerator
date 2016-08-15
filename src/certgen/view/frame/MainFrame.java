package certgen.view.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.KeyStore;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import certgen.controller.action.ActionManager;
import certgen.view.element.MainMenuBar;
import certgen.view.element.MainToolBar;

/**
 * Represents main frame of application.
 * It is realized using singleton pattern. 
 * 
 * @author Dražen Đanić
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -3928909859611520131L;
	private static MainFrame instance;
	private KeyStore currentKS;
	private String currentKSFilePath;
	private boolean changedKS;
	private ActionManager actionManager;
	
	private MainFrame() {
		
	}
	
	private void initialize() {	
		actionManager = new ActionManager();
		
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
				actionManager.getExitApplicationAction().actionPerformed(null);
			}
		});
		setJMenuBar(new MainMenuBar(actionManager));
		add(new MainToolBar(actionManager), BorderLayout.NORTH);
	}
	
	/**
	 * Gets singleton instance of this class.
	 * 
	 * @return instance of this class
	 */
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
			instance.initialize();
		}
		
		return instance;
	}
	
	/**
	 * Sets current key store.
	 * 
	 * @param currentKS - new current key store
	 */
	public void setCurrentKS(KeyStore currentKS) {
		this.currentKS = currentKS;
	}
	
	/**
	 * Gets current key store.
	 * 
	 * @return current key store
	 */
	public KeyStore getCurrentKS() {
		return currentKS;
	}
	
	/**
	 * Sets file path to file of current key store.
	 * 
	 * @param currentKSFilePath - new path to file of current key store
	 */
	public void setCurrentKSFilePath(String currentKSFilePath) {
		this.currentKSFilePath = currentKSFilePath;
	}
	
	/**
	 * Gets path to file of current key store.
	 * 
	 * @return file path
	 */
	public String getCurrentKSFilePath() {
		return currentKSFilePath;
	}
	
	/**
	 * Set new value of indicator which gives information about changes done on current key store.
	 *  
	 * @param changedKS - new value of indicator
	 */
	public void setChangedKS(boolean changedKS) {
		this.changedKS = changedKS; 
	}
	
	/**
	 * Determines whether current key store is changed and created.
	 * 
	 * @return <code>true</code> if current key store is changed and created.
	 * 			Otherwise returns <code>false</code>
	 */
	public boolean isChangedKS() {
		return changedKS;
	}
	
	/**
	 * Sets new action manager.
	 * 
	 * @param actionManager - new action manager
	 */
	public void setActionManager(ActionManager actionManager) {
		this.actionManager = actionManager;
	}
	
	/**
	 * Gets current action manager.
	 * 
	 * @return current action manager
	 */
	public ActionManager getActionManager() {
		return actionManager;
	}

}
