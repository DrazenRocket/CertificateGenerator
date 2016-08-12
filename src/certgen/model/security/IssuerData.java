package certgen.model.security;

import java.security.PrivateKey;

import org.bouncycastle.asn1.x500.X500Name;

/**
 * Represents POJO with basic information about issuer.
 * 
 * @author Dražen Đanić
 */
public class IssuerData {

	private X500Name x500Name;
	private PrivateKey privateKey;
	
	public IssuerData() {
		
	}
	
	public IssuerData(X500Name x500Name, PrivateKey privateKey) {
		this.x500Name = x500Name;
		this.privateKey = privateKey;
	}
	
	public void setX500Name(X500Name x500Name) {
		this.x500Name = x500Name;
	}
	
	public X500Name getX500Name() {
		return x500Name;
	}
	
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	
}
