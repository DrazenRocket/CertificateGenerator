package certgen.util.security;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import certgen.model.security.IssuerData;
import certgen.model.security.SubjectData;

/**
 * Class which offers methods for basic manipulation with certificates. 
 * For example, it offers methods for generating 
 * certificates, storing, loading etc.
 * 
 * @author Dražen Đanić
 */
public class CertificateUtil {
	
	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	/**
	 * Generates a key pair using RSA algorithm.
	 * Size of keys is 1024 bits.
	 * 
	 * @return the generated key pair or <code>null</code> if
	 * 		   an error happened
	 */
	public static KeyPair generateKeyPair() {
		KeyPair keyPair = null;
		
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(1024);
			
			keyPair = keyPairGen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return keyPair;
	}
	
	/**
	 * Generate certificate in X.509v3 format.
	 * Certificate is signed using <code>SHA256WithRSAEncryption</code> algorithm.
	 * 
	 * @param issuerData - information about issuer
	 * @param subjectData - information about subject
	 * @return the generated certificate or <code>null</code> if an error happened
	 */
	public static X509Certificate generateCertificate(IssuerData issuerData, SubjectData subjectData) {
		X509Certificate x509Certificate = null;
			
		try {
			JcaContentSignerBuilder contentSignerBuilder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
			contentSignerBuilder = contentSignerBuilder.setProvider("BC");
			
			ContentSigner contentSigner = contentSignerBuilder.build(issuerData.getPrivateKey());
			
			X509v3CertificateBuilder certificateBuilder = new JcaX509v3CertificateBuilder(issuerData.getX500Name(), 
																						  new BigInteger(subjectData.getSerialNumber()), 
																						  subjectData.getStartDate(), 
																						  subjectData.getEndDate(), 
																						  subjectData.getX500Name(), 
																						  subjectData.getPublicKey());
			X509CertificateHolder certificateHolder = certificateBuilder.build(contentSigner);
			
			JcaX509CertificateConverter certificateConverter = new JcaX509CertificateConverter();
			certificateConverter = certificateConverter.setProvider("BC");
			
			x509Certificate = certificateConverter.getCertificate(certificateHolder);
		} catch (OperatorCreationException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		}
		
		return x509Certificate;
	}

	/**
	 * Reads the first certificate from file.
	 * Returned certificate is in X.509 format. If an error
	 * occurred, this method will return <code>null</code>.
	 * 
	 * @param filePath - path to file from which you want to read certificate
	 * @return X.509 formated certificate or <code>null</code> if an error occurred
	 */
	public static Certificate readFromFile(String filePath) {
		Certificate certificate = null;
		
		try {
			FileInputStream fis = new FileInputStream(filePath);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			certificate = cf.generateCertificate(bis);
			
			bis.close();
			fis.close();
			
			System.out.println("Certificate readed from file: " + filePath);
			System.out.println(certificate + "\n\n\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return certificate;
	}
	
	/**
	 * Reads all certificates from file.
	 * Returned certificates are in X.509 format. If an error occurred,
	 * this method will return <code>null</code>.
	 * 
	 * @param filePath - path to file from which you want to read certificates
	 * @return collection of X.509 formated certificates or <code>null</code> if an error occurred
	 */
	public static Collection<? extends Certificate> readAllFromFile(String filePath) {
		Collection<? extends Certificate> certificates = null;
		
		try {
			FileInputStream fis = new FileInputStream(filePath);
			
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			certificates = cf.generateCertificates(fis);
			
			fis.close();
			
			System.out.println("Certificates readed from file: " + filePath);
			System.out.println(certificates + "\n\n\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return certificates;
	}
	
	/**
	 * Saves certificate in file.
	 * The content of file is in PEM format (Base64 encoded).
	 * 
	 * @param certificate - the certificate which will be saved
	 * @param filePath - path to file where certificate will be saved
	 * @return indicator of success
	 */
	public static boolean saveToPEMFile(Certificate certificate, String filePath) {
		boolean success = false;
		
		try {
			FileWriter fw = new FileWriter(filePath);
			PEMWriter pw = new PEMWriter(fw);
			
			pw.writeObject(certificate);
			pw.flush();
			
			pw.close();
			fw.close();
			
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	/**
	 * Saves certificate in file.
	 * The content of file is in DER format (binary encoded).
	 * 
	 * @param certificate - the certificate which will be saved
	 * @param filePath - path to file where certificate will be saved
	 * @return indicator of success
	 */
	public static boolean saveToDERFile(Certificate certificate, String filePath) {
		boolean success = false;
		
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			
			fos.write(certificate.getEncoded());
			fos.flush();
			
			fos.close();
			
			success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CertificateEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
}
