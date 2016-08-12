package certgen.util.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

/**
 * Class which offers methods for basic manipulations with key stores.
 * For example, it offers method for creating, loading and saving key stores.
 * 
 * @author Dražen Đanić
 */
public class KeyStoreUtil {

	/**
	 * Loads key store from specified file path or create new empty if 
	 * parameter <code>filePath</code> has value <code>null</code>.
	 * A <code>password</code> may be used to unlock key store.
	 * If an error happened, this method will return <code>null</code>.
	 * 
	 * @param filePath - path to file from which key store will be loaded of null
	 * 				   if you want empty key store
	 * @param password - argument used to unlock key store
	 * @return loaded key store or <code>null</code> if an error occurred
	 */
	public static KeyStore loadKeyStore(String filePath, char[] password) {
		KeyStore keyStore = null;
		
		try {
			keyStore = KeyStore.getInstance("JKS", "SUN");
			
			if (filePath != null) {
				FileInputStream fis = new FileInputStream(filePath);
				
				keyStore.load(fis, password);
				
				fis.close();
			} else {
				keyStore.load(null, password);
			}
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return keyStore;
	}
	
	/**
	 * Saves passed key store in file which path is passed as argument and protects its integrity with the given password.
	 * 
	 * @param keyStore - key store which will be saved
	 * @param filePath - path to file where key store will be save
	 * @param password - password which will be used to protect integrity 
	 * @return indicator of success
	 */
	public static boolean saveKeyStore(KeyStore keyStore, String filePath, char[] password) {
		boolean success = false;
		
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			
			keyStore.store(fos, password);
			
			fos.close();
			
			success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	/**
	 * Assigns the given key to the given alias in the given keyStore, protecting it with the given password.
	 * If the given alias already exists, the key store information associated with it is overridden by the given key.
	 *  
	 * @param keyStore - key store in which will be set key entry
	 * @param alias - the alias name
	 * @param privateKey - the key to be associated with the alias
	 * @param password - the password to protect the key
	 * @param certificate - the certificate for the corresponding public key 
	 * @return indicator of success
	 */
	public static boolean setKeyEntry(KeyStore keyStore, String alias, PrivateKey privateKey, char[] password, Certificate certificate) {
		boolean success = false;
		
		try {
			keyStore.setKeyEntry(alias, privateKey, password, new Certificate[] {certificate});
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
}