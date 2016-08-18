package certgen.model.table;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * Represents table model which contains certificates.
 * 
 * @author Dražen Đanić
 */
public class CertificateTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 5927032790873006586L;
	
	public CertificateTableModel() {
		setColumnIdentifiers(new Object[]{"Alias", "Type", "Creation date"});  
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return false;
	}
	
	/**
	 * Adds rows to end of table model. Each row represents
	 * one entry from passed key store.
	 * 
	 * @param keyStore - key store with entries
	 */
	public void addRows(KeyStore keyStore) {
		try {
			Enumeration<String> aliases = keyStore.aliases();
			
			while (aliases.hasMoreElements()) {
				addRow(aliases.nextElement(), keyStore);
			}
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a row to the end of the model.
	 * Added row represent one entry with alias from passed key store.
	 * 
	 * @param alias - alias of entry from key store
	 * @param keyStore - key store which have given alias
	 */
	public void addRow(String alias, KeyStore keyStore) {
		try {
			Vector<String> rowData = new Vector<String>();
			rowData.add(alias);
			
			if (keyStore.isCertificateEntry(alias)) {
				rowData.add("Certificate Entry");
			} else if (keyStore.isKeyEntry(alias)) {
				rowData.add("Key Entry");
			} else {
				rowData.add("");
			}
			
			rowData.add(keyStore.getCreationDate(alias).toString());
			
			addRow(rowData);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
	}

}
