package certgen.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import certgen.util.ImageUtil;

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
		putValue(SMALL_ICON, ImageUtil.loadImage(getClass().getResource("/certgen/resource/img/exit-icon.png"), 16, 16));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO
		// For now!
		System.exit(0);
	}

}
