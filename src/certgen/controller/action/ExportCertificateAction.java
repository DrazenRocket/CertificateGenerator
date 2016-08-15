package certgen.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Extended AbstractAction class which performs necessary things for exporting certificate in new file.
 * 
 * @author Dražen Đanić
 */
public class ExportCertificateAction extends AbstractAction {

	private static final long serialVersionUID = -2060060976015860705L;
	
	public ExportCertificateAction() {
		putValue(NAME, "Export Certificate");
		putValue(SHORT_DESCRIPTION, "Export selected certificate in file");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
