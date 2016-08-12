package certgen.model.security;

import java.security.PublicKey;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500Name;

/**
 * Represents POJO with information about subject. Also,
 * have some information about certificate.
 *
 * @author Dražen Đanić
 */
public class SubjectData {

	private X500Name x500Name;
	private PublicKey publicKey;
	private String serialNumber;
	private Date startDate;
	private Date endDate;
	
	public SubjectData() {
		
	}
	
	public SubjectData(X500Name x500Name, PublicKey publicKey, String serialNumber, Date startDate, Date endDate) {
		this.x500Name = x500Name;
		this.publicKey = publicKey;
		this.serialNumber = serialNumber;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public void setX500Name(X500Name x500Name) {
		this.x500Name = x500Name;
	}
	
	public X500Name getX500Name() {
		return x500Name;
	}
	
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	
	public PublicKey getPublicKey() {
		return publicKey;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
}
