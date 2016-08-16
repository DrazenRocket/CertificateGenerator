package certgen.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import certgen.util.ImageUtil;

/**
 * Extended AbstractAction class which performs necessary things for new saving key store to file.
 *  
 * @author Dražen Đanić
 *
 */
public class SaveAsKeyStoreAction extends AbstractAction {

	private static final long serialVersionUID = -7049295203087653165L;
	
	public SaveAsKeyStoreAction() {
		putValue(NAME, "Save As Keystore");
		putValue(SHORT_DESCRIPTION, "Save key store to new file");
		putValue(SMALL_ICON, ImageUtil.loadImageIcon(getClass().getResource("/certgen/resource/img/save_as-icon.png"), 20, 20));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
