package certgen.model.table;

import javax.swing.table.DefaultTableModel;

/**
 * Represents table model which contains certificates.
 * 
 * @author Dražen Đanić
 */
public class CertificateTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 5927032790873006586L;
	
	public CertificateTableModel() {
		setColumnIdentifiers(new Object[]{"No", "Alias", "Type"});
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return false;
	}

}
