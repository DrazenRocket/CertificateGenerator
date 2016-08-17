package certgen.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import certgen.util.ImageUtil;
import certgen.view.frame.MainFrame;

/**
 * Extended AbstractAction class which performs necessary things for exiting application. 
 * 
 * @author Dražen Đanić
 */
public class ExitApplicationAction extends AbstractAction {

	private static final long serialVersionUID = 8698556893635553804L;
	
	public ExitApplicationAction() {
		putValue(NAME, "Exit");
		putValue(SHORT_DESCRIPTION, "Exit application");
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/exit-icon.png"), 20, 20));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame mf = MainFrame.getInstance();
		
		if (mf.isChangedKS()) {
			int answer = JOptionPane.showConfirmDialog(null, "Do you want to save chages of the current keystore first?", "Save changes?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if (answer == JOptionPane.YES_OPTION) {
				mf.getActionManager().getSaveKeyStoreAction().actionPerformed(null);
				// TODO enable exiting after saving (it will require to add new methods in classes for save and save as) which will return indicator
			} else if (answer == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		} else {
			System.exit(0);
		}
	}

}
